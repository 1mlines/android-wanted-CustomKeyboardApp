package com.hugh.data.repository

import com.hugh.model.ClipBoardData
import kotlinx.coroutines.flow.Flow

interface ClipBoardRepository {

    suspend fun insertClipData(data: ClipBoardData)

    suspend fun deleteClipData(id: Long)

    fun getClipsFlow(): Flow<List<ClipBoardData>>
}