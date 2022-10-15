package com.preonboarding.customkeyboard.domain.model

import com.preonboarding.customkeyboard.data.local.entity.ClipboardEntity

data class Clipboard(
    val clipData: String,
) {
    fun toEntity(): ClipboardEntity {
        return ClipboardEntity(
            clipData = this.clipData
        )
    }
}
