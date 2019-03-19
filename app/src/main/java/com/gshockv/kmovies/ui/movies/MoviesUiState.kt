package com.gshockv.kmovies.ui.movies

import com.gshockv.kmovies.data.model.MoviesList

sealed class MoviesUiState {
    object LoadingState : MoviesUiState() {}
    class ErrorState(val error: String) : MoviesUiState()
    class DataState(val data: MoviesList) : MoviesUiState()
}
