package com.hugh.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hugh.data.room.model.ClipBoardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface KeyboardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClip(clipData: ClipBoardEntity)

    @Query("DELETE FROM ClipBoardEntity WHERE id = :id")
    fun deleteClip(id: Long)

    @Query("SELECT * FROM ClipBoardEntity")
    fun getClips(): Flow<List<ClipBoardEntity>>

}