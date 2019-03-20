package com.gshockv.kmovies.data.di

import com.gshockv.kmovies.data.MoviesDownloader
import com.gshockv.kmovies.data.MoviesRepository
import com.gshockv.kmovies.data.api.MoviesDbApi
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideMoviesRepository(moviesApi: MoviesDbApi) : MoviesRepository {
        return MoviesDownloader(moviesApi)
    }
}
