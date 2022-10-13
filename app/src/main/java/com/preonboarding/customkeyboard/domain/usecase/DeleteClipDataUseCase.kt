package com.preonboarding.customkeyboard.domain.usecase

import com.preonboarding.customkeyboard.domain.repository.ClipboardRepository
import javax.inject.Inject

class DeleteClipDataUseCase @Inject constructor(
    private val clipboardRepository: ClipboardRepository
) {
    suspend operator fun invoke(id: Int) {
        clipboardRepository.deleteClipData(id)
    }
}