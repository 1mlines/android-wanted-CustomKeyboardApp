package com.preonboarding.customkeyboard.data.di

import android.content.Context
import androidx.room.Room
import com.preonboarding.customkeyboard.data.local.WantedDatabase
import com.preonboarding.customkeyboard.data.local.dao.BookmarkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): WantedDatabase =
        Room.databaseBuilder(context, WantedDatabase::class.java, "wanted.db").build()

    @Singleton
    @Provides
    fun provideBookmarkDao(database: WantedDatabase): BookmarkDao =
        database.bookmarkDao()
}
