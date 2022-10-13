package com.preonboarding.customkeyboard.domain.repository

import com.preonboarding.customkeyboard.domain.model.Clipboard
import kotlinx.coroutines.flow.Flow

interface ClipboardRepository {

    fun getAllClipData(): Flow<List<Clipboard>>

    suspend fun insertClipData(clipData: String)

    suspend fun deleteClipData(id: Int)
}