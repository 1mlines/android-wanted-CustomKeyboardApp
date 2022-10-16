package com.preonboarding.customkeyboard.presentation.common.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("bind_image")
fun ImageView.bindImageSource(source: Int) {
    this.setImageResource(source)
}

@BindingAdapter("bind_visible")
fun View.bindVisibility(condition: Boolean) {
    if(condition) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}
