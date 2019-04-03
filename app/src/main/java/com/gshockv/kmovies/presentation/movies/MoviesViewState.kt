package com.gshockv.kmovies.presentation.movies

import com.gshockv.kmovies.data.model.MoviesResponse

sealed class MoviesViewState {
    object LoadingState : MoviesViewState() {}
    data class ErrorState(val error: Throwable) : MoviesViewState()
    data class DataState(val data: MoviesResponse) : MoviesViewState()
}
