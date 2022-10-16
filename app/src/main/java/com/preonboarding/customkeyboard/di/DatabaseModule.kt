package com.preonboarding.customkeyboard.di

import android.content.Context
import androidx.room.Room
import com.preonboarding.customkeyboard.common.DATABASE_NAME
import com.preonboarding.customkeyboard.data.local.dao.ClipboardDao
import com.preonboarding.customkeyboard.data.local.database.ClipboardDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideClipboardDatabase(
        @ApplicationContext context: Context
    ): ClipboardDatabase =
        Room.databaseBuilder(
            context,
            ClipboardDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideClipboardDao(clipboardDatabase: ClipboardDatabase): ClipboardDao =
        clipboardDatabase.clipboardDao()
}