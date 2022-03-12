package com.achareh.sample.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.achareh.component.ui.fragment.BaseFragment
import com.achareh.sample.R
import com.achareh.sample.databinding.FragmentAddressBinding
import com.achareh.sample.ui.adapter.AddressItem
import com.achareh.sample.viewModel.AddressViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddressFragment : BaseFragment<FragmentAddressBinding>() {

    private val vm by viewModel<AddressViewModel>()

    override fun setLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean,
    ) = FragmentAddressBinding.inflate(inflater, container, false)

    override fun onInitObject() {
        observeAddressList()
    }

    override fun onInitView() {
        vm.getAddressList()
        binding.fbtAddAddress.setOnClickListener {
            findNavController().navigate(R.id.action_addressFragment_to_createAddressFragment)
        }
    }

    private fun observeAddressList() = vm.addressList.handleResponse(
        onSuccess = {
            it?.result?.apply {
                binding.rvAddress.adapter = AddressItem(this)
            }
            progress.dismiss()
        },
        retryErrorConnection = {
            vm.getAddressList()
        }
    )
}