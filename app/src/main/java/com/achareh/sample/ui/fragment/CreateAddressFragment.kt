package com.achareh.sample.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.achareh.component.ui.fragment.FormClass
import com.achareh.component.ui.view.InputText
import com.achareh.data.model.body.BAddress
import com.achareh.sample.R
import com.achareh.sample.databinding.FragmentCreateAddressBinding

class CreateAddressFragment : FormClass<FragmentCreateAddressBinding>() {

    private var gender = "male"

    override fun setLayoutBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean,
    ) = FragmentCreateAddressBinding.inflate(inflater, container, false)

    override fun onInitObject() {

    }

    override fun onInitView() {
        binding.btnConfirm.setOnClickListener {
            if (!isValidInputs())
                return@setOnClickListener

            findNavController().navigate(R.id.action_createAddressFragment_to_mapFragment, bundleOf(
                "createAddress" to address()
            ))

        }

        binding.toggleSex.addOnButtonCheckedListener { _, checkedId, _ ->
            gender = if (checkedId == R.id.male) "male" else "female"
        }
    }

    override val inputs: MutableList<InputText>
        get() = mutableListOf(binding.inputName, binding.inputFamily, binding.inputMoblie, binding.inputPhoneNumber)

    private fun address() = BAddress(
        address = binding.inputAddress.text.trim(),
        last_name = binding.inputFamily.text.trim(),
        first_name = binding.inputName.text.trim(),
        gender = gender,
        lat = 0.0,
        lng = 0.0,
        coordinate_mobile = binding.inputMoblie.text.trim(),
        coordinate_phone_number = binding.inputPhoneNumber.text.trim()
    )
}