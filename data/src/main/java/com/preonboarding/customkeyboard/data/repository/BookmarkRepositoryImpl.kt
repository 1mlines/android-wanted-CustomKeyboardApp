package com.preonboarding.customkeyboard.data.repository

import com.preonboarding.customkeyboard.data.source.BookmarkDataSource
import com.preonboarding.customkeyboard.domain.model.Bookmark
import com.preonboarding.customkeyboard.domain.repository.BookmarkRepository
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkDataSource: BookmarkDataSource
) : BookmarkRepository {
    override suspend fun saveBookmark(bookmark: Bookmark) =
        bookmarkDataSource.saveBookmark(bookmark)

    override suspend fun getBookmarks(): List<Bookmark> =
        bookmarkDataSource.getBookmarks()

    override suspend fun deleteBookmark(bookmark: Bookmark) =
        bookmarkDataSource.deleteBookmark(bookmark)
}
