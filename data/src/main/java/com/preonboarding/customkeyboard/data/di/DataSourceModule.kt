package com.preonboarding.customkeyboard.data.di

import com.preonboarding.customkeyboard.data.source.BookmarkDataSource
import com.preonboarding.customkeyboard.data.source.BookmarkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsBookmarkDataSource(sourceImpl: BookmarkDataSourceImpl): BookmarkDataSource
}
