package com.gshockv.kmovies.data.domain

import com.gshockv.kmovies.data.MoviesRepository
import com.gshockv.kmovies.data.api.ApiResult
import com.gshockv.kmovies.data.model.MoviesResponse
import javax.inject.Inject

class DiscoverMoviesInteractor
        @Inject constructor(private val moviesRepo: MoviesRepository) : DiscoverMoviesUseCase {

    override suspend fun discoverMovies(): ApiResult<MoviesResponse> {
        return moviesRepo.discoverMovies()
    }

}
