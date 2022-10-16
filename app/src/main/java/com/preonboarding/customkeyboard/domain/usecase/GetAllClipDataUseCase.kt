package com.preonboarding.customkeyboard.domain.usecase

import com.preonboarding.customkeyboard.domain.model.Clipboard
import com.preonboarding.customkeyboard.domain.repository.ClipboardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllClipDataUseCase @Inject constructor(
    private val clipboardRepository: ClipboardRepository
) {
    operator fun invoke(): Flow<List<Clipboard>> {
        return clipboardRepository.getAllClipData()
    }
}