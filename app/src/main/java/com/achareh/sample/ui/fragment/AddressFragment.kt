package com.achareh.sample.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.achareh.component.ui.fragment.BaseFragment
import com.achareh.sample.databinding.FragmentAddressBinding

class AddressFragment : BaseFragment<FragmentAddressBinding>() {

    override fun setLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) = FragmentAddressBinding.inflate(inflater, container, false)

    override fun onInitObject() {
    }

    override fun onInitView() {
    }
}