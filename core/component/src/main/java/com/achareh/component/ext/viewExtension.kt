package com.achareh.component.ext

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.finish(){
    requireActivity().finish()
}

fun View.color(@ColorRes color: Int) = ContextCompat.getColor(context, color)

val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

fun View.visible() = apply { visibility = View.VISIBLE }
fun View.invisible() = apply { visibility = View.INVISIBLE }
fun View.gone() = apply { visibility = View.GONE }

fun View.setBackgroundCircle(
    @ColorInt color: Int = Color.DKGRAY,
    strokeWidth: Int = 1,
    @ColorInt strokeColor: Int = Color.WHITE,
) {
    val corner = GradientDrawable()
    corner.shape = GradientDrawable.OVAL
    corner.color = ColorStateList.valueOf(color)
    corner.setSize(width, height)

    if (strokeWidth > 0)
        corner.setStroke(strokeWidth.dp, strokeColor)

    isEnabled = true
    background = corner
}