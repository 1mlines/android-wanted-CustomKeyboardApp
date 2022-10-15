package com.preonboarding.customkeyboard.presentation.clipboard

import com.preonboarding.customkeyboard.data.local.entity.ClipboardEntity

interface ClipboardActionListener {
    fun deleteClipData(clipboard: ClipboardEntity)

    fun copyClipData(clipData: String)
}