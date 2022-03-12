package com.achareh.component.ext

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment

inline fun Context.constraintLayout(
    vararg view: View,
    content: ConstraintSet.(ConstraintLayout) -> Unit,
): ConstraintLayout {
    val constraintLayout = ConstraintLayout(this).apply {
        id = View.generateViewId()
        layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        view.map { m -> addView(m) }
    }

    val constraint = ConstraintSet()
    constraint.clone(constraintLayout)
    content.invoke(constraint, constraintLayout)
    constraint.applyTo(constraintLayout)

    return constraintLayout
}

inline fun View.constraintLayout(
    vararg view: View,
    content: ConstraintSet.(ConstraintLayout) -> Unit,
): ConstraintLayout {
    val constraintLayout = ConstraintLayout(context).apply {
        id = View.generateViewId()
        layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        removeAllViews()

        view.map { m ->
            if (m.parent != null)
                (m.parent as ViewGroup).removeView(m)
            addView(m)
        }
    }

    val constraint = ConstraintSet()
    constraint.clone(constraintLayout)
    content.invoke(constraint, constraintLayout)
    constraint.applyTo(constraintLayout)

    return constraintLayout
}

inline fun Fragment.constraintLayout(vararg view: View, constraint: ConstraintLayout.() -> Unit): ConstraintLayout {
    val constraintLayout = ConstraintLayout(requireContext()).apply {
        id = View.generateViewId()
        layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        view.map { m -> addView(m) }
    }
    constraint.invoke(constraintLayout)

    return constraintLayout
}

inline fun ConstraintLayout.setPosition(constraintSet: (ConstraintSet) -> Unit) {
    val constraint = ConstraintSet()
    constraint.clone(this)
    constraintSet.invoke(constraint)
    constraint.applyTo(this)
}

fun ConstraintSet.topTop(view1: View, view2: View, margin: Int = 0) {
    connect(
        view1.id,
        ConstraintSet.TOP,
        view2.id,
        ConstraintSet.TOP,
        margin.dp
    )
}

fun ConstraintSet.bottomBottom(view1: View, view2: View, margin: Int = 0) {
    connect(
        view1.id,
        ConstraintSet.BOTTOM,
        view2.id,
        ConstraintSet.BOTTOM,
        margin.dp
    )
}

fun ConstraintSet.endEnd(view1: View, view2: View, margin: Int = 0) {
    connect(
        view1.id,
        ConstraintSet.END,
        view2.id,
        ConstraintSet.END,
        margin.dp
    )
}

fun ConstraintSet.startStart(view1: View, view2: View, margin: Int = 0) {
    connect(
        view1.id,
        ConstraintSet.START,
        view2.id,
        ConstraintSet.START,
        margin.dp
    )
}

fun ConstraintSet.topBottom(view1: View, view2: View, margin: Int = 0) {
    connect(
        view1.id,
        ConstraintSet.TOP,
        view2.id,
        ConstraintSet.BOTTOM,
        margin.dp
    )
}

fun ConstraintSet.bottomTop(view1: View, view2: View, margin: Int = 0) {
    connect(
        view1.id,
        ConstraintSet.BOTTOM,
        view2.id,
        ConstraintSet.TOP,
        margin.dp
    )
}

fun ConstraintSet.endStart(view1: View, view2: View, margin: Int = 0) {
    connect(
        view1.id,
        ConstraintSet.END,
        view2.id,
        ConstraintSet.START,
        margin.dp
    )
}

fun ConstraintSet.startEnd(view1: View, view2: View, margin: Int = 0) {
    connect(
        view1.id,
        ConstraintSet.START,
        view2.id,
        ConstraintSet.END,
        margin.dp
    )
}
