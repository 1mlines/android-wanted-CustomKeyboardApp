package com.preonboarding.customkeyboard.presentation.ui.pay

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.preonboarding.customkeyboard.presentation.R

class PayDialog(context: Context) : Dialog(context) {

    init {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_pay)
    }
}
