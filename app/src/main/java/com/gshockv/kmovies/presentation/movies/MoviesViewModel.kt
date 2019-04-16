package com.gshockv.kmovies.presentation.movies

import com.gshockv.kmovies.data.domain.DiscoverMoviesUseCase
import com.gshockv.kmovies.presentation.BaseViewModel
import com.gshockv.kmovies.presentation.ViewStateStore
import kotlinx.coroutines.*
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val discoverUseCase: DiscoverMoviesUseCase
) : BaseViewModel(dispatcher) {

    companion object {
        const val TAG = "MoviesViewModel"
    }

    val store = ViewStateStore<MoviesViewState>(MoviesViewState.LoadingState)

    fun loadMoviesList() {
        uiScope.launch {
            val state = discoverUseCase.discoverMovies()
            store.dispatchState(state)
        }
    }
}
