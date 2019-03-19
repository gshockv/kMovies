package com.gshockv.kmovies.data.model

import com.google.gson.annotations.SerializedName

class MoviesList {
    @SerializedName("results")
    var movies = arrayListOf<Movie>()
}
