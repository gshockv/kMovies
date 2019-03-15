package com.gshockv.kmovies.ui.movies

import com.gshockv.kmovies.data.model.MoviesList

sealed class MoviesListState {
    object LoadingState : MoviesListState() {}
    class ErrorState(val error: String) : MoviesListState()
    class DataState(val movies: MoviesList) : MoviesListState()
}
