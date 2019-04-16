package com.gshockv.kmovies.data.di

import com.gshockv.kmovies.data.MoviesDownloader
import com.gshockv.kmovies.data.MoviesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideMoviesRepository(impl: MoviesDownloader) : MoviesRepository
}
