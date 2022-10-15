package com.preonboarding.customkeyboard.di

import com.preonboarding.customkeyboard.data.usecase.RoomUseCaseImpl
import com.preonboarding.customkeyboard.domain.usecase.RoomUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
abstract class UseCaseModule {

//    @Provides
//    @ServiceScoped
//    fun provideGetAllClipDataUseCase(clipboardRepository: ClipboardRepository): GetAllClipDataUseCase {
//        return GetAllClipDataUseCase(clipboardRepository)
//    }
//
//    @Provides
//    @ServiceScoped
//    fun provideDeleteClipDataUseCase(clipboardRepository: ClipboardRepository): DeleteClipDataUseCase {
//        return DeleteClipDataUseCase(clipboardRepository)
//    }
//
//    @Provides
//    @ServiceScoped
//    fun provideInsertClipDataUseCase(clipboardRepository: ClipboardRepository): InsertClipDataUseCase {
//        return InsertClipDataUseCase(clipboardRepository)
//    }

    @Binds
    @ServiceScoped
    abstract fun bindRoomUseCase(roomUseCaseImpl: RoomUseCaseImpl): RoomUseCase
}