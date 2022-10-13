package com.hugh.data.repository

import com.hugh.model.ClipBoardState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClipBoardRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : ClipBoardRepository {

    override suspend fun insertClipData(data: ClipBoardState) {
        localDataSource.insertClipData(data)
    }

    override suspend fun deleteClipData(data: ClipBoardState) {
        localDataSource.deleteClipData(data)
    }

    override fun getClipsFlow(): Flow<List<ClipBoardState>> {
        return localDataSource.getClipsFlow()
    }
}