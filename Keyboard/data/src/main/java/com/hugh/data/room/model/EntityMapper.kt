package com.hugh.data.room.model

import com.hugh.model.ClipBoardData


fun ClipBoardEntity.asModel() = ClipBoardData.EMPTY.copy(
    id = this.id,
    text = this.clipData
)

fun ClipBoardData.asEntity() = ClipBoardEntity.EMPTY.copy(
    id = this.id,
    clipData = this.text
)