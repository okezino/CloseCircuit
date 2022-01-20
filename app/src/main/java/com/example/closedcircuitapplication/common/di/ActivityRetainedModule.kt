package com.example.closedcircuitapplication.common.di

import com.example.closedcircuitapplication.common.utils.CoroutineDispatchersProvider
import com.example.closedcircuitapplication.common.utils.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {

    @Binds
    abstract fun bindDispatchersProvider(dispatcherProvider: CoroutineDispatchersProvider): DispatcherProvider

}