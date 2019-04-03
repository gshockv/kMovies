package com.gshockv.kmovies.data.domain

import com.gshockv.kmovies.data.MoviesRepository
import com.gshockv.kmovies.data.api.ApiResult
import com.gshockv.kmovies.presentation.movies.MoviesViewState
import javax.inject.Inject

class DiscoverMoviesInteractor
        @Inject constructor(private val moviesRepo: MoviesRepository) : DiscoverMoviesUseCase {

    override suspend fun discoverMovies(): MoviesViewState {
        val apiResult = moviesRepo.discoverMovies()
        return when (apiResult) {
            is ApiResult.Success -> MoviesViewState.DataState(apiResult.data)
            is ApiResult.Failure -> MoviesViewState.ErrorState(apiResult.exception)
        }
    }
}
