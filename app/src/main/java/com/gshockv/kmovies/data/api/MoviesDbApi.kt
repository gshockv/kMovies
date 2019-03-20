package com.gshockv.kmovies.data.api

import com.gshockv.kmovies.data.model.MoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface MoviesDbApi {
    @GET("discover/movie")
    fun discoverMovies() : Deferred<Response<MoviesResponse>>
}
