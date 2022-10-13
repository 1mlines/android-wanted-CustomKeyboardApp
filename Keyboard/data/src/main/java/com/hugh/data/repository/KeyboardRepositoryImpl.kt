package com.hugh.data.repository

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class KeyboardRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : KeyboardRepository {

    override suspend fun insertClipData(data: String) {
        localDataSource.insertClipData(data)
    }

    override fun getClipsFlow(): Flow<List<String>> {
        return localDataSource.getClipsFlow()
    }
}