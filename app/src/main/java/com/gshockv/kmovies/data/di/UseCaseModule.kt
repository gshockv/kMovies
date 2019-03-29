package com.gshockv.kmovies.data.di

import com.gshockv.kmovies.data.MoviesRepository
import com.gshockv.kmovies.data.domain.DiscoverMoviesInteractor
import com.gshockv.kmovies.data.domain.DiscoverMoviesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideDiscoverMoviesUseCase(moviesRepo: MoviesRepository) : DiscoverMoviesUseCase {
        return DiscoverMoviesInteractor(moviesRepo)
    }

}
