package com.hugh.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hugh.data.room.model.ClipBoardEntity

@Database(
    entities = [ClipBoardEntity::class],
    version = KeyboardDatabase.ROOM_VERSION,
    exportSchema = false
)
abstract class KeyboardDatabase : RoomDatabase() {

    abstract fun getKeyboardDao() : KeyboardDao

    companion object {
        const val ROOM_VERSION = 2
    }
}