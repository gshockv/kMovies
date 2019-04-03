package com.gshockv.kmovies.presentation.movies

import com.gshockv.kmovies.data.domain.DiscoverMoviesUseCase
import com.gshockv.kmovies.presentation.BaseViewModel
import com.gshockv.kmovies.presentation.ViewStateStore
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val discoverUseCase: DiscoverMoviesUseCase) : BaseViewModel() {
    companion object {
        const val TAG = "MoviesViewModel"
    }

    val store = ViewStateStore<MoviesViewState>(MoviesViewState.LoadingState)

    fun loadMoviesList() {
        launch {
            val state = discoverUseCase.discoverMovies()
            withContext(Main) {
                store.dispatchState(state)
            }
        }
    }
}
