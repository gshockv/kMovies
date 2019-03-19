package com.gshockv.kmovies.data

import com.gshockv.kmovies.data.api.ApiResult
import com.gshockv.kmovies.data.model.MoviesResponse

interface MoviesRepository {
    suspend fun discoverMovies() : ApiResult<MoviesResponse>
}
