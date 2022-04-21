package com.example.closedcircuitapplication.beneficiary.settings.di
import com.example.closedcircuitapplication.beneficiary.settings.data.repository.SettingsRepository
import com.example.closedcircuitapplication.beneficiary.settings.domain.repository.SettingsRepositoryInterface
import com.example.closedcircuitapplication.common.common.data.network.webservice.BaseService
import com.example.closedcircuitapplication.common.common.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object SettingsModule {

    @Provides
    fun provideSettingsRepositoryInterface(
        api: BaseService,
        dispatcher: DispatcherProvider
    ): SettingsRepositoryInterface {
        return SettingsRepository(api,dispatcher)
    }
}