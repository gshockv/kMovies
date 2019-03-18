package com.gshockv.kmovies.data.api

import com.gshockv.kmovies.data.model.MoviesList
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

const val BASE_URL = "https://api.themoviedb.org/3"

interface MoviesDbApi {

    @GET("/discover/movie")
    fun discoverMovies() : Deferred<MoviesList>

}
