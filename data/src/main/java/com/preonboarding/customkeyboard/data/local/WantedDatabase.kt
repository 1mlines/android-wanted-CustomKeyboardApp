package com.preonboarding.customkeyboard.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.preonboarding.customkeyboard.data.local.dao.BookmarkDao
import com.preonboarding.customkeyboard.data.local.entity.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 1, exportSchema = false)
abstract class WantedDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}
