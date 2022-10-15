package com.preonboarding.customkeyboard.presentation.clipboard

interface ClipboardActionListener {
    fun deleteClipData(id: Int)

    fun copyClipData(text: String)

    fun getClipData()
}