package com.example.closedcircuitapplication.settings.di

import com.example.closedcircuitapplication.common.data.network.Api
import com.example.closedcircuitapplication.common.data.repository.SettingsRepository
import com.example.closedcircuitapplication.common.domain.repository.SettingsRepositoryInterface
import com.example.closedcircuitapplication.common.utils.DispatcherProvider
import com.example.closedcircuitapplication.settings.data.mapper.SettingsDtoToDomainMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object SettingsModule {

    @Provides
    fun provideSettingsRepositoryInterface(
        api: Api,
        mapper: SettingsDtoToDomainMapper,
        dispatcher: DispatcherProvider
    ): SettingsRepositoryInterface {
        return SettingsRepository(api,mapper,dispatcher)
    }
}