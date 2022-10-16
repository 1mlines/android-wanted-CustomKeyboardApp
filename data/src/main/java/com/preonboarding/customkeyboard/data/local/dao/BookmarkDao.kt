package com.preonboarding.customkeyboard.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.preonboarding.customkeyboard.data.local.entity.BookmarkEntity

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBookmark(bookmarkEntity: BookmarkEntity)

    @Query("SELECT * FROM BookmarkEntity")
    suspend fun getBookmarks(): List<BookmarkEntity>

    @Delete
    suspend fun deleteBookmark(bookmarkEntity: BookmarkEntity)
}
