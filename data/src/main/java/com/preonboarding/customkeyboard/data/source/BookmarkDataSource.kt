package com.preonboarding.customkeyboard.data.source

import com.preonboarding.customkeyboard.domain.model.Bookmark

interface BookmarkDataSource {
    suspend fun saveBookmark(bookmark: Bookmark)
    suspend fun getBookmarks(): List<Bookmark>
    suspend fun deleteBookmark(bookmark: Bookmark)
}
