package com.gshockv.kmovies.presentation.movies

import com.gshockv.kmovies.data.model.MoviesResponse

sealed class MoviesUiState {
    object LoadingState : MoviesUiState() {}
    class ErrorState(val error: String) : MoviesUiState()
    class DataState(val data: MoviesResponse) : MoviesUiState()
}
