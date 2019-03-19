package com.gshockv.kmovies.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int = 0,
    val title: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("backdrop_path")
    val backdropImage: String,

    val overview: String
)

val Movie.posterImageUrl : String
    get() = "https://image.tmdb.org/t/p/original${this.backdropImage}"
