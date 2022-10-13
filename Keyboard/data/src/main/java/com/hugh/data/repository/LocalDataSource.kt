package com.hugh.data.repository

import androidx.room.withTransaction
import com.hugh.data.room.KeyboardDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val keyboardDatabase: KeyboardDatabase
) {
    private val keyboardDao = keyboardDatabase.keyboardDao

    suspend fun insertClipData(data: String) {
        keyboardDatabase.withTransaction {
            keyboardDao.insertClip(data)
        }
    }

    fun getClipsFlow(): Flow<List<String>> {
        return keyboardDao.getClips()
    }
}