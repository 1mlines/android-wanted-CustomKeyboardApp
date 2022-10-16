package com.preonboarding.customkeyboard.domain.usecase

import com.preonboarding.customkeyboard.domain.model.Bookmark
import com.preonboarding.customkeyboard.domain.repository.BookmarkRepository
import javax.inject.Inject

class DeleteBookmarkUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    suspend operator fun invoke(bookmark: Bookmark) =
        repository.deleteBookmark(bookmark)
}
