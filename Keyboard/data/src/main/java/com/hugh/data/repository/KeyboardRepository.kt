package com.hugh.data.repository

import kotlinx.coroutines.flow.Flow

interface KeyboardRepository {

    suspend fun insertClipData(data: String)

    fun getClipsFlow(): Flow<List<String>>
}