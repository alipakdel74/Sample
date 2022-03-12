package com.achareh.component.ui.view

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputFilter
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.setPadding
import androidx.core.widget.doOnTextChanged
import com.achareh.component.R
import com.achareh.component.ext.*
import com.achareh.component.vlidator.Validator
import com.google.android.material.card.MaterialCardView
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class InputText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : MaterialCardView(context, attrs, defStyleAttr), KoinComponent {

    private val validator by inject<Validator>()

    private var validationType = ValidationType.NAME

    var isValid = false

    var text: String
        get() {
            return editText.text?.toString() ?: ""
        }
        set(value) {
            editText.setText(value)
        }

    private val editText = AppCompatEditText(context).apply {
        id = View.generateViewId()
        layoutParams = ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
        textSize = 14f
        setTextColor(color(R.color.black))
        setHintTextColor(color(R.color.text_light))
        typeface = Typeface.createFromAsset(resources.assets, "iran_yekan_medium.ttf")
        setPadding(10.dp)
        setBackgroundColor(Color.TRANSPARENT)
        gravity = Gravity.START
    }

    private val textView = CTextView(context).apply {
        id = View.generateViewId()
        layoutParams = ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
        textSize = 14f
        setTextColor(color(R.color.text_primary))
        setPadding(10.dp)
        setFontType(CTextView.FontStyle.BOLD)
        setBackgroundColor(Color.TRANSPARENT)
    }

    private val icon = AppCompatImageView(context).apply {
        id = View.generateViewId()
        layoutParams = ViewGroup.LayoutParams(20.dp, 20.dp)
        setColorFilter(color(R.color.white))
        setPadding(4.dp)
        setBackgroundCircle(color(R.color.text_light))
    }

    private val constraintLayout = constraintLayout(icon, editText, textView) {
        it.layoutParams =
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        topTop(icon, it, 10)
        endEnd(icon, it, 10)


        topTop(textView, it)
        startStart(textView, it, 10)

        topTop(editText, it)
        startEnd(editText, textView, 10)
        endStart(editText, icon, 10)

    }

    init {
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        addView(constraintLayout)

        attrs?.apply {
            val typedArray = context.obtainStyledAttributes(this, R.styleable.InputText)
            typedArray.apply {
                textView.text = getString(R.styleable.InputText_hint).orEmpty()

                val inputText =
                    getInt(R.styleable.InputText_android_inputType, 0)

                if (inputText != 0) editText.inputType = inputText

                editText.minLines = getInt(R.styleable.InputText_android_minLines, 1)
                editText.maxLines = getInt(R.styleable.InputText_android_maxLines, editText.minLines)
                editText.imeOptions = getInt(R.styleable.InputText_android_imeOptions, EditorInfo.IME_ACTION_NEXT)
                editText.textSize = getFloat(R.styleable.InputText_android_textSize, 14f)
                editText.filters = arrayOf(
                    InputFilter.LengthFilter(
                        getInt(R.styleable.InputText_android_maxLength, 1000)
                    )
                )

                if (editText.minLines <= 1) {
                    constraintLayout.setPosition {
                        it.topTop(icon, constraintLayout)
                        it.bottomBottom(icon, constraintLayout)
                        it.bottomBottom(textView, constraintLayout)
                        it.bottomBottom(editText, constraintLayout)
                    }
                }

                val typeValidation = getInt(R.styleable.InputText_validation_type, ValidationType.NAME.ordinal)
                validationType =
                    ValidationType.values().find { f -> f.ordinal == typeValidation } ?: ValidationType.NAME

                editText.doOnTextChanged { text, _, _, _ ->
                    when (validationType) {
                        ValidationType.ADDRESS -> isValid(validator.isValidAddress(text))
                        ValidationType.NAME -> isValid(validator.isValidName(text))
                        ValidationType.MOBILE -> isValid(validator.isValidMobile(text))
                        ValidationType.PHONE_NUMBER -> isValid(validator.isValidPhoneNumber(text))
                    }

                    if (editText.minLines <= 1) {
                        constraintLayout.setPosition {
                            it.topTop(icon, constraintLayout)
                            it.bottomBottom(icon, constraintLayout)
                            it.bottomBottom(textView, constraintLayout)
                            it.bottomBottom(editText, constraintLayout)
                        }
                    }
                }

            }
            typedArray.recycle()
        }
    }

    private fun isValid(validation: Boolean): Boolean {
        isValid = if (validation) {
            icon.setImageResource(R.drawable.ic_done)
            icon.setBackgroundCircle(color(R.color.primary))
            true
        } else {
            icon.setBackgroundCircle(color(R.color.text_light))
            icon.setImageDrawable(null)
            false
        }
        return isValid
    }

    enum class ValidationType { MOBILE, PHONE_NUMBER, NAME, ADDRESS }

}