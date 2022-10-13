package com.preonboarding.customkeyboard.data.repository

import com.preonboarding.customkeyboard.data.local.entity.ClipboardEntity
import com.preonboarding.customkeyboard.data.local.source.ClipboardDataSource
import com.preonboarding.customkeyboard.di.DefaultDispatcher
import com.preonboarding.customkeyboard.domain.mapper.mapToClipboard
import com.preonboarding.customkeyboard.domain.model.Clipboard
import com.preonboarding.customkeyboard.domain.repository.ClipboardRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ClipboardRepositoryImpl @Inject constructor(
    private val dataSource: ClipboardDataSource,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ClipboardRepository {

    override fun getAllClipData(): Flow<List<Clipboard>> = flow {
        val result = dataSource.getAllClipData().mapToClipboard()

        emit(result)
    }.flowOn(defaultDispatcher)

    override suspend fun insertClipData(clipData: String) {
        dataSource.insertClipData(
            ClipboardEntity(clipData = clipData)
        )
    }

    override suspend fun deleteClipData(id: Int) {
        dataSource.deleteClipData(id)
    }
}