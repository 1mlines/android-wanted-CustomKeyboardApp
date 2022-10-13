package com.hugh.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ClipBoardTable(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "clipData") val clipData: String
) {

    companion object {
        val EMPTY = ClipBoardTable(
            id = 0,
            clipData = ""
        )
    }
}