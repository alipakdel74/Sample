package com.achareh.component.vlidator

class ValidatorImpl : Validator {

    override fun isValidMobile(input: CharSequence?): Boolean =
        input != null && Patterns.PATTERN_MOBILE.toRegex().matches(input.toString())

    override fun isValidPhoneNumber(input: CharSequence?): Boolean =
        input != null && Patterns.PATTERN_PHONE_NUMBER.toRegex().matches(input.toString())

    override fun isValidName(input: CharSequence?, minLength: Int): Boolean =
        input != null && input.length >= 3

    override fun isValidAddress(input: CharSequence?): Boolean =
        input != null && input.length >= 10
}