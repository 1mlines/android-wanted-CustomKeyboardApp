package com.hugh.data.repository

import androidx.room.withTransaction
import com.hugh.data.room.KeyboardDatabase
import com.hugh.data.room.model.asEntity
import com.hugh.data.room.model.asModel
import com.hugh.model.ClipBoardState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val keyboardDatabase: KeyboardDatabase
) {
    private val keyboardDao = keyboardDatabase.getKeyboardDao()

    suspend fun insertClipData(data: ClipBoardState) {
        keyboardDatabase.withTransaction {
            keyboardDao.insertClip(data.asEntity())
        }
    }

    suspend fun deleteClipData(data: ClipBoardState) {
        keyboardDatabase.withTransaction {
            keyboardDao.deleteClip(data.id)
        }
    }

    fun getClipsFlow(): Flow<List<ClipBoardState>> {
        return keyboardDao.getClips().map { clips ->
            clips.map {
                it.asModel()
            }
        }
    }
}