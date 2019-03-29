package com.gshockv.kmovies.data.model

import com.google.gson.annotations.SerializedName

class MoviesResponse {
    var page: Int = 1

    @SerializedName("results")
    var movies = arrayListOf<Movie>()
}
