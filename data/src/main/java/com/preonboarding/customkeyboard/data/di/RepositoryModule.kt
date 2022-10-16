package com.preonboarding.customkeyboard.data.di

import com.preonboarding.customkeyboard.data.repository.BookmarkRepositoryImpl
import com.preonboarding.customkeyboard.domain.repository.BookmarkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsBookmarkRepository(repository: BookmarkRepositoryImpl): BookmarkRepository
}
