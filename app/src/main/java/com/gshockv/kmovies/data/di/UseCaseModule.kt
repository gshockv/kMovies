package com.gshockv.kmovies.data.di

import com.gshockv.kmovies.data.domain.DiscoverMoviesInteractor
import com.gshockv.kmovies.data.domain.DiscoverMoviesUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {

    @Binds
    abstract fun provideDiscoverMoviesUseCase(impl: DiscoverMoviesInteractor) : DiscoverMoviesUseCase
}
