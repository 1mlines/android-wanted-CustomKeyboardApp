package com.hugh.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ClipBoardTable::class],
    version = KeyboardDatabase.ROOM_VERSION,
    exportSchema = false
)
abstract class KeyboardDatabase : RoomDatabase() {

    abstract val keyboardDao: KeyboardDao

    companion object {
        const val ROOM_VERSION = 1
    }
}