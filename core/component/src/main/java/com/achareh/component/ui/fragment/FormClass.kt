package com.achareh.component.ui.fragment

import androidx.viewbinding.ViewBinding
import com.achareh.component.ui.view.InputText
import com.achareh.component.vlidator.Validator
import org.koin.android.ext.android.inject

abstract class FormClass<VB : ViewBinding> : BaseFragment<VB>() {

    private val validator by inject<Validator>()

    abstract val inputs: MutableList<InputText>

    fun isValidInputs(): Boolean {
        if (inputs.find { f -> !f.isValid } != null)
            return false
        return true
    }

}