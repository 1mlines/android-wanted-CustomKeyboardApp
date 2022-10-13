package com.hugh.data.repository

import com.hugh.model.ClipBoardData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClipBoardRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : ClipBoardRepository {

    override suspend fun insertClipData(data: ClipBoardData) {
        localDataSource.insertClipData(data)
    }

    override suspend fun deleteClipData(id: Long) {
        localDataSource.deleteClipData(id)
    }

    override fun getClipsFlow(): Flow<List<ClipBoardData>> {
        return localDataSource.getClipsFlow()
    }
}