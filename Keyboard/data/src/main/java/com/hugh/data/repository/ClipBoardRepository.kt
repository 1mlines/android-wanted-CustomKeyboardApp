package com.hugh.data.repository

import com.hugh.model.ClipBoardState
import kotlinx.coroutines.flow.Flow

interface ClipBoardRepository {

    suspend fun insertClipData(data: ClipBoardState)

    suspend fun deleteClipData(data: ClipBoardState)

    fun getClipsFlow(): Flow<List<ClipBoardState>>
}