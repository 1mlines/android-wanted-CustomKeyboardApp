package com.preonboarding.customkeyboard.data.local.dao

import androidx.room.*
import com.preonboarding.customkeyboard.data.local.entity.ClipboardEntity

@Dao
interface ClipboardDao {

    @Query("SELECT * FROM `Clipboard.db`")
    suspend fun getAllClipData(): List<ClipboardEntity>

    @Delete
    suspend fun deleteClipData(clipboard: ClipboardEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClipData(clipboardEntity: ClipboardEntity)

    @Query("SELECT * FROM `Clipboard.db` WHERE clipData = :clipData")
    suspend fun getClipData(clipData: String): ClipboardEntity
}