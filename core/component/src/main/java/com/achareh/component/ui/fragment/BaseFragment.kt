package com.achareh.component.ui.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.achareh.component.ui.view.SnackBarBuilder
import com.achareh.data.network.ResultResponse
import com.achareh.data.network.ThrowableCode
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

abstract class BaseFragment<b : ViewBinding> : Fragment() {

    private var _binding: b? = null
    val binding get() = _binding!!

    val progress by lazy { ProgressDialog(requireContext()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onInitObject()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = setLayoutBinding(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onInitView()
    }

    abstract fun setLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean = false,
    ): b

    abstract fun onInitObject()

    abstract fun onInitView()


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun <T> SharedFlow<ResultResponse<T>>.handleResponse(
        onSuccess: (ResultResponse.Success<T>?) -> Unit,
        retryErrorConnection: (() -> Unit)? = null,
    ) {
        lifecycleScope.launchWhenCreated {
            onEach { response ->
                when (response) {
                    is ResultResponse.Loading -> progress.show()
                    is ResultResponse.Success -> onSuccess.invoke(response)
                    is ResultResponse.Error -> {
                        if (response.code == ThrowableCode.ERROR_DISCONNECTED.code) {
                            showMessage("عدم اتصال به اینترنت") {
                                retryErrorConnection?.invoke()
                            }
                        } else
                            showMessage(response.message ?: "خطای ناشناخته")
                        progress.dismiss()
                    }


                }
            }.collect()
        }
    }

    fun showMessage(
        message: String, action: View.OnClickListener? = null,
    ) {
        if (action == null)
            SnackBarBuilder().setMessage(message).show(requireView())
        else
            SnackBarBuilder().setMessage(message)
                .setActionText("سعی مجدد")
                .setDuration(Snackbar.LENGTH_INDEFINITE)
                .setAction(action).show(requireView())
    }

}
