package com.achareh.component.ui.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<b : ViewBinding> : Fragment() {

    private var _binding: b? = null
    val binding get() = _binding!!

    val progressBar by lazy { ProgressDialog(requireContext()) }


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

}
