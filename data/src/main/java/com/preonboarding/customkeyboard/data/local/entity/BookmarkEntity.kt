package com.preonboarding.customkeyboard.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.preonboarding.customkeyboard.domain.model.Bookmark

@Entity
data class BookmarkEntity(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
)

fun BookmarkEntity.toBookmark(): Bookmark {
    return Bookmark(this.name)
}

fun Bookmark.toBookmarkEntity(): BookmarkEntity {
    return BookmarkEntity(this.name)
}
