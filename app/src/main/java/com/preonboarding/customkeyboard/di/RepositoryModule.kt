package com.preonboarding.customkeyboard.di

import com.preonboarding.customkeyboard.data.repository.ClipboardRepositoryImpl
import com.preonboarding.customkeyboard.domain.repository.ClipboardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindClipboardRepository(
        clipboardRepositoryImpl: ClipboardRepositoryImpl
    ): ClipboardRepository
}