package com.gshockv.kmovies.data.di

import com.gshockv.kmovies.BuildConfig
import com.gshockv.kmovies.data.model.ApiKey
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    @Provides
    fun provideApiKey() : ApiKey = ApiKey(BuildConfig.API_KEY)
}
