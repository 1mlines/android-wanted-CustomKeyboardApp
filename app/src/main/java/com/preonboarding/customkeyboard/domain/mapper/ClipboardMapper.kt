package com.preonboarding.customkeyboard.domain.mapper

import com.preonboarding.customkeyboard.data.local.entity.ClipboardEntity
import com.preonboarding.customkeyboard.domain.model.Clipboard

fun List<ClipboardEntity>.mapToClipboard(): List<Clipboard> {
    val clipboardList = mutableListOf<Clipboard>()

    this.map { entity ->
        clipboardList.add(
            Clipboard(
                clipData = entity.clipData,
            )
        )
    }

    return clipboardList
}