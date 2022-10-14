package com.preonboarding.customkeyboard.data.usecase

import com.preonboarding.customkeyboard.domain.model.Clipboard
import com.preonboarding.customkeyboard.domain.repository.ClipboardRepository
import com.preonboarding.customkeyboard.domain.usecase.RoomUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomUseCaseImpl @Inject constructor(
    private val clipboardRepository: ClipboardRepository
) : RoomUseCase {

    override suspend fun deleteClipData(id: Int) {
        clipboardRepository.deleteClipData(id)
    }

    override suspend fun insertClipData(data: String) {
        clipboardRepository.insertClipData(data)
    }

    override fun getAllClipData(): Flow<List<Clipboard>> {
        return clipboardRepository.getAllClipData()
    }
}