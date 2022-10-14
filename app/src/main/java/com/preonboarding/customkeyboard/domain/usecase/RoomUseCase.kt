package com.preonboarding.customkeyboard.domain.usecase

import com.preonboarding.customkeyboard.domain.model.Clipboard
import kotlinx.coroutines.flow.Flow

interface RoomUseCase {

    suspend fun deleteClipData(id: Int)

    suspend fun insertClipData(data: String)

    fun getAllClipData(): Flow<List<Clipboard>>
}