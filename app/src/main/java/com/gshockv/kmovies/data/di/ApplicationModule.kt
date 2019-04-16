package com.gshockv.kmovies.data.di

import com.gshockv.kmovies.BuildConfig
import com.gshockv.kmovies.data.model.ApiKey
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class ApplicationModule {
    @Provides
    fun provideApiKey() : ApiKey = ApiKey(BuildConfig.API_KEY)

    @Provides
    fun provideMainCoroutineDispatcher() : CoroutineDispatcher = Dispatchers.Main
}
