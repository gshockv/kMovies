package com.gshockv.kmovies

import com.gshockv.kmovies.data.api.ApiResult
import com.gshockv.kmovies.data.model.Movie
import com.gshockv.kmovies.data.model.MoviesResponse
import java.lang.Exception

object TestData {
    val FIRST_PAGE = 1

    val testMovies = arrayListOf(
        Movie(1, "Movie_1"),
        Movie(2, "Movie_2"),
        Movie(3, "Movie_3"),
        Movie(4, "Movie_4"),
        Movie(5, "Movie_5"),
        Movie(6, "Movie_6"),
        Movie(7, "Movie_7"),
        Movie(8, "Movie_8"),
        Movie(9, "Movie_9"),
        Movie(110, "Movie_10")
    )

    val testMovieSize = testMovies.size

    val testSuccessfullApiResult = MoviesResponse().apply {
        page = FIRST_PAGE
        movies = testMovies
    }

    val testFailureApiResult = ApiResult.Failure(Exception("Fail"))
}
