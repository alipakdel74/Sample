package com.achareh.component.ui.view

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.achareh.component.R
import com.google.android.material.snackbar.Snackbar

class SnackBarBuilder {

    private var snackBar: Snackbar? = null

    private var message = ""
    private var actionText = ""
    private var action: View.OnClickListener? = null
    private var duration = Snackbar.LENGTH_SHORT
    private var animation = Snackbar.ANIMATION_MODE_FADE

    private var messageTextColor = snackMessageTextColor
    private var actionTextColor = snackActionTextColor
    private var actionBackgroundColor = snackActionBackgroundColor

    fun setMessage(
        message: String,
        @ColorRes textColor: Int = snackMessageTextColor,
    ) = apply {
        this.message = message
        messageTextColor = textColor
    }

    fun setActionText(
        text: String = "",
        @ColorRes textColor: Int = snackActionTextColor,
        @ColorRes backgroundColor: Int = snackActionBackgroundColor,
    ) = apply {
        actionText = text
        actionTextColor = textColor
        actionBackgroundColor = backgroundColor
    }

    fun setAction(action: View.OnClickListener? = null) = apply {
        this.action = action
    }

    fun setDuration(duration: Int) = apply {
        this.duration = duration
    }

    fun setAnimation(animation: Int) = apply {
        this.animation = animation
    }

    fun dismiss() = apply {
        if (isSnackBar != null) {
            snackBar = isSnackBar
            snackBar?.dismiss()
        }
    }


    @SuppressLint("ShowToast")
    fun show(v: View) {
        snackBar = if (action != null)
            Snackbar.make(
                v, message, duration
            ).setAction(actionText, action)
                .setAnimationMode(animation)
                .setBackgroundTint(
                    ContextCompat.getColor(
                        v.context,
                        snackBackgroundColor
                    )
                )
        else Snackbar.make(
            v, message, duration
        ).setAnimationMode(animation)
            .setBackgroundTint(
                ContextCompat.getColor(
                    v.context,
                    snackBackgroundColor
                )
            )

        snackBar?.apply {
            val text: TextView = view.findViewById(R.id.snackbar_text)
            val actionText: TextView = view.findViewById(R.id.snackbar_action)
            text.typeface = Typeface.createFromAsset(context.assets, "iran_yekan_medium.ttf")
            actionText.typeface = Typeface.createFromAsset(context.assets, "iran_yekan_medium.ttf")

            actionText.setTextColor(ContextCompat.getColor(view.context, actionTextColor))
            actionText.setBackgroundColor(
                ContextCompat.getColor(
                    view.context,
                    actionBackgroundColor
                )
            )

            text.setTextColor(ContextCompat.getColor(view.context, messageTextColor))

            if (!isShown)
                show()
            if (action != null)
                isSnackBar = this
        }

    }

    companion object SnackBarOption {
        @ColorRes
        var snackMessageTextColor = R.color.white

        @ColorRes
        var snackBackgroundColor = R.color.black

        @ColorRes
        var snackActionTextColor = R.color.black

        @ColorRes
        var snackActionBackgroundColor = R.color.primary

        private var isSnackBar: Snackbar? = null

    }
}