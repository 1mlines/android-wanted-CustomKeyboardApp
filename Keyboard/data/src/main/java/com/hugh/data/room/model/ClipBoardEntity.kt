package com.hugh.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ClipBoardEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "clipData") val clipData: String
) {

    companion object {
        val EMPTY = ClipBoardEntity(
            id = 0,
            clipData = ""
        )
    }
}