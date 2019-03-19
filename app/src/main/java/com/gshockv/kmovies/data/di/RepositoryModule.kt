package com.gshockv.kmovies.data.di

import com.gshockv.kmovies.data.MoviesDownloader
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object RepositoryModule {

    @JvmStatic
    @Reusable
    @Provides
    fun provideMoviesRepository() : MoviesDownloader {
        return MoviesDownloader()
    }

}
