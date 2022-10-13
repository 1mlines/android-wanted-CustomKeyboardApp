package com.preonboarding.customkeyboard.di

import com.preonboarding.customkeyboard.data.local.source.ClipboardDataSource
import com.preonboarding.customkeyboard.data.local.source.ClipboardDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatasourceModule {

    @Singleton
    @Binds
    abstract fun bindsClipboardDataSource(
        clipboardDataSourceImpl: ClipboardDataSourceImpl
    ): ClipboardDataSource
}