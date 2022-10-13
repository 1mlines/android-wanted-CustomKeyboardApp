package com.hugh.data.room.model

import com.hugh.model.ClipBoardState


fun ClipBoardEntity.asModel() = ClipBoardState.EMPTY.copy(
    id = this.id,
    text = this.clipData
)

fun ClipBoardState.asEntity() = ClipBoardEntity.EMPTY.copy(
    id = this.id,
    clipData = this.text
)