package com.preonboarding.customkeyboard.di

import com.preonboarding.customkeyboard.domain.repository.ClipboardRepository
import com.preonboarding.customkeyboard.domain.usecase.DeleteClipDataUseCase
import com.preonboarding.customkeyboard.domain.usecase.GetAllClipDataUseCase
import com.preonboarding.customkeyboard.domain.usecase.InsertClipDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetAllClipDataUseCase(clipboardRepository: ClipboardRepository): GetAllClipDataUseCase {
        return GetAllClipDataUseCase(clipboardRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteClipDataUseCase(clipboardRepository: ClipboardRepository): DeleteClipDataUseCase {
        return DeleteClipDataUseCase(clipboardRepository)
    }

    @Provides
    @Singleton
    fun provideInsertClipDataUseCase(clipboardRepository: ClipboardRepository): InsertClipDataUseCase {
        return InsertClipDataUseCase(clipboardRepository)
    }
}