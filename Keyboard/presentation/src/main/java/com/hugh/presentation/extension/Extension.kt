package com.hugh.presentation.extension

import android.content.res.Resources
import android.util.TypedValue

fun Int.dip() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics
).toInt()