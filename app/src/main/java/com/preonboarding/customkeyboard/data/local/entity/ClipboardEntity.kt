package com.preonboarding.customkeyboard.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.preonboarding.customkeyboard.common.DATABASE_NAME

@Entity(tableName = DATABASE_NAME)
data class ClipboardEntity(
    @PrimaryKey
    val clipData: String
)