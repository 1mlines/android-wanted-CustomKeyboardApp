package com.preonboarding.customkeyboard.data.local.source

import com.preonboarding.customkeyboard.data.local.dao.ClipboardDao
import com.preonboarding.customkeyboard.data.local.entity.ClipboardEntity
import javax.inject.Inject

class ClipboardDataSourceImpl @Inject constructor(
    private val clipboardDao: ClipboardDao
) : ClipboardDataSource {

    override suspend fun getAllClipData(): List<ClipboardEntity> {
        return clipboardDao.getAllClipData()
    }

    override suspend fun insertClipData(clipboardEntity: ClipboardEntity) {
        return clipboardDao.insertClipData(clipboardEntity)
    }

    override suspend fun deleteClipData(clipboard: ClipboardEntity) {
        return clipboardDao.deleteClipData(clipboard)
    }

    override suspend fun getClipData(clipData: String): ClipboardEntity {
        return clipboardDao.getClipData(clipData)
    }
}