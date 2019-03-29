package com.gshockv.kmovies.data.domain

import com.gshockv.kmovies.data.api.ApiResult
import com.gshockv.kmovies.data.model.MoviesResponse

interface DiscoverMoviesUseCase {
    suspend fun discoverMovies() : ApiResult<MoviesResponse>
}
