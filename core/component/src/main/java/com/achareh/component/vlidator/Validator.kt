package com.achareh.component.vlidator

interface Validator {

    fun isValidMobile(input: CharSequence?): Boolean

    fun isValidPhoneNumber(input: CharSequence?): Boolean

    fun isValidName(input: CharSequence?, minLength: Int = 0): Boolean

    fun isValidAddress(input: CharSequence?): Boolean

}