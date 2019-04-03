package com.gshockv.kmovies.data.domain

import com.gshockv.kmovies.presentation.movies.MoviesViewState

interface DiscoverMoviesUseCase {
    suspend fun discoverMovies() : MoviesViewState
}
