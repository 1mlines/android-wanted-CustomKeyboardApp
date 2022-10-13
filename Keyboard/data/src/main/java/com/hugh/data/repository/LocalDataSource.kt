package com.hugh.data.repository

import androidx.room.withTransaction
import com.hugh.data.room.KeyboardDatabase
import com.hugh.data.room.model.asEntity
import com.hugh.data.room.model.asModel
import com.hugh.model.ClipBoardData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val keyboardDatabase: KeyboardDatabase
) {
    private val keyboardDao = keyboardDatabase.getKeyboardDao()

    suspend fun insertClipData(data: ClipBoardData) {
        keyboardDatabase.withTransaction {
            keyboardDao.insertClip(data.asEntity())
        }
    }

    suspend fun deleteClipData(id: Long) {
        keyboardDatabase.withTransaction {
            keyboardDao.deleteClip(id)
        }
    }

    fun getClipsFlow(): Flow<List<ClipBoardData>> {
        return keyboardDao.getClips().map { clips ->
            clips.map {
                it.asModel()
            }
        }
    }
}