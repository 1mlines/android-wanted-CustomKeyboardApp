package com.preonboarding.customkeyboard.domain.usecase

import com.preonboarding.customkeyboard.domain.repository.ClipboardRepository
import javax.inject.Inject

class InsertClipDataUseCase @Inject constructor(
    private val clipboardRepository: ClipboardRepository
) {
    suspend operator fun invoke(clipData: String) {
        clipboardRepository.insertClipData(clipData)
    }
}