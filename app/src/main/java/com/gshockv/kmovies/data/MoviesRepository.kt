package com.gshockv.kmovies.data

import com.gshockv.kmovies.data.model.Movie
import com.gshockv.kmovies.data.model.MoviesList

class MoviesRepository {
    fun moviesList() : MoviesList = MoviesList().apply {
        for (i in 0..10) {
            movies.add(
                Movie(i + 1,
                    "Movie_$i",
                    "12-May-2019",
                    "bg_$i"
                )
            )
        }
    }
}
