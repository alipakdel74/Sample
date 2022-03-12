package com.achareh.component.ui.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton

class CButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : MaterialButton(context, attrs) {


    init {
        typeface = Typeface.createFromAsset(resources.assets, "iran_yekan_black.ttf")

    }

}