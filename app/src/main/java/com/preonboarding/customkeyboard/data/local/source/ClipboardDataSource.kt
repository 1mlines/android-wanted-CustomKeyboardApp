package com.preonboarding.customkeyboard.data.local.source

import com.preonboarding.customkeyboard.data.local.entity.ClipboardEntity

interface ClipboardDataSource {
    suspend fun getAllClipData(): List<ClipboardEntity>

    suspend fun insertClipData(clipboardEntity: ClipboardEntity)

    suspend fun deleteClipData(id: Int)
}