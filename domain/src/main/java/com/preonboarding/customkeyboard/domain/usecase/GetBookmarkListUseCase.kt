package com.preonboarding.customkeyboard.domain.usecase

import com.preonboarding.customkeyboard.domain.model.Bookmark
import com.preonboarding.customkeyboard.domain.repository.BookmarkRepository
import javax.inject.Inject

class GetBookmarkListUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    suspend operator fun invoke(): List<Bookmark> =
        repository.getBookmarks()
}
