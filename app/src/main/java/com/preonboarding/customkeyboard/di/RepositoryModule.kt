package com.preonboarding.customkeyboard.di

import com.preonboarding.customkeyboard.data.repository.ClipboardRepositoryImpl
import com.preonboarding.customkeyboard.domain.repository.ClipboardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ServiceComponent::class)
abstract class RepositoryModule {

    @Binds
    @ServiceScoped
    abstract fun bindClipboardRepository(
        clipboardRepositoryImpl: ClipboardRepositoryImpl
    ): ClipboardRepository
}