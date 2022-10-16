package com.preonboarding.customkeyboard.data.source

import com.preonboarding.customkeyboard.data.local.dao.BookmarkDao
import com.preonboarding.customkeyboard.data.local.entity.toBookmark
import com.preonboarding.customkeyboard.data.local.entity.toBookmarkEntity
import com.preonboarding.customkeyboard.domain.model.Bookmark
import javax.inject.Inject

class BookmarkDataSourceImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao
) : BookmarkDataSource {
    override suspend fun saveBookmark(bookmark: Bookmark) =
        bookmarkDao.saveBookmark(bookmark.toBookmarkEntity())

    override suspend fun getBookmarks(): List<Bookmark> =
        bookmarkDao.getBookmarks().map { it.toBookmark() }

    override suspend fun deleteBookmark(bookmark: Bookmark) =
        bookmarkDao.deleteBookmark(bookmark.toBookmarkEntity())
}
