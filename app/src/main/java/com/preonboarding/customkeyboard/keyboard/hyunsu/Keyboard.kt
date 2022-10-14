package com.preonboarding.customkeyboard.keyboard.hyunsu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.LinearLayout
import com.preonboarding.customkeyboard.databinding.ViewKeyboardActionBinding

abstract class Keyboard(
    context: Context,
    layoutInflater: LayoutInflater,
    listener: KeyboardActionListener
) {

    private val height = 150
    protected val config = context.resources.configuration

    protected val binding = ViewKeyboardActionBinding.inflate(layoutInflater)
    protected val keyboardActionListener = listener
    var inputConnection: InputConnection? = null

    protected fun setLayoutParamsLandScape(layout: LinearLayout) {
        layout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            (height * 0.7).toInt()
        )
    }

    protected fun setLayoutParams(layout: LinearLayout) {
        layout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            height
        )
    }

    abstract fun init()
    abstract fun changeMode()
    abstract fun setLayoutComponents()
    abstract fun doSpaceClick(): View.OnClickListener
    abstract fun doEnterClick(): View.OnClickListener
    abstract fun doShiftClick(): View.OnClickListener
    abstract fun doDeleteClick(): View.OnClickListener
}
