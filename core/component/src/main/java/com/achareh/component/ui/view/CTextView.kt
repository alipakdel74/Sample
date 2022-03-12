package com.achareh.component.ui.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import com.achareh.component.R
import com.google.android.material.textview.MaterialTextView

class CTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : MaterialTextView(context, attrs) {

    init {
        if (attrs != null) handleAttributes(attrs)
    }

    private fun handleAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CTextView)

        // Typeface
        val fontStyleValue =
            typedArray.getInteger(R.styleable.CTextView_CFontStyle, FontStyle.MEDIUM.ordinal)
        setTypeFace(fontStyleValue)

        typedArray.recycle()
    }

    private fun setTypeFace(fontStyleValue: Int) {
        val font = when (fontStyleValue) {
            FontStyle.MEDIUM.ordinal -> "iran_yekan_medium.ttf"
            FontStyle.BOLD.ordinal -> "iran_yekan_black.ttf"
            else -> "iran_yekan_medium.ttf"
        }

        typeface = Typeface.createFromAsset(resources.assets, font)
    }

    enum class FontStyle { MEDIUM, BOLD }

}