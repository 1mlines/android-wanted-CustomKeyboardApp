package com.hugh.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class KeyboardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertClip(data: String)

    @Query("SELECT * FROM ClipBoardTable")
    abstract fun getClips(): Flow<List<String>>

}