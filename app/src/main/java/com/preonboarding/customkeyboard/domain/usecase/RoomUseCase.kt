package com.preonboarding.customkeyboard.domain.usecase

import com.preonboarding.customkeyboard.data.local.entity.ClipboardEntity
import com.preonboarding.customkeyboard.domain.model.Clipboard
import kotlinx.coroutines.flow.Flow

interface RoomUseCase {

    suspend fun deleteClipData(clipboard: ClipboardEntity)

    suspend fun insertClipData(data: String)

    fun getAllClipData(): Flow<List<Clipboard>>

    suspend fun getClipData(clipData: String): ClipboardEntity
}