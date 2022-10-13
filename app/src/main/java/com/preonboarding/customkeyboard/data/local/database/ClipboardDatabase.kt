package com.preonboarding.customkeyboard.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.preonboarding.customkeyboard.data.local.dao.ClipboardDao
import com.preonboarding.customkeyboard.data.local.entity.ClipboardEntity

@Database(
    entities = [ClipboardEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ClipboardDatabase : RoomDatabase() {
    abstract fun clipboardDao(): ClipboardDao
}