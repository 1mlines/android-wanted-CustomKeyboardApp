package com.preonboarding.customkeyboard.domain.repository

import com.preonboarding.customkeyboard.domain.model.Bookmark

interface BookmarkRepository {
    suspend fun saveBookmark(bookmark: Bookmark)
    suspend fun getBookmarks(): List<Bookmark>
    suspend fun deleteBookmark(bookmark: Bookmark)
}
