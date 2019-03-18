package com.gshockv.kmovies.data.di

import com.gshockv.kmovies.data.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object RepositoryModule {

    @JvmStatic
    @Reusable
    @Provides
    fun provideMoviesRepository() : MoviesRepository {
        return MoviesRepository()
    }

}
