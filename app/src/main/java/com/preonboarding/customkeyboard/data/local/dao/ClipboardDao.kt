package com.preonboarding.customkeyboard.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.preonboarding.customkeyboard.data.local.entity.ClipboardEntity

@Dao
interface ClipboardDao {

    @Query("SELECT * FROM `Clipboard.db`")
    suspend fun getAllClipData(): List<ClipboardEntity>

    @Query("DELETE FROM `Clipboard.db` WHERE id = :id")
    suspend fun deleteClipData(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClipData(clipboardEntity: ClipboardEntity)
}