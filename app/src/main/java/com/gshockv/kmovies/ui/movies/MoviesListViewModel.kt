package com.gshockv.kmovies.ui.movies

import androidx.lifecycle.MutableLiveData
import com.gshockv.kmovies.data.MoviesRepository
import com.gshockv.kmovies.ui.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MoviesListViewModel : BaseViewModel() {
    private val state = MutableLiveData<MoviesListState>()

    private val moviesRepo = MoviesRepository()

    fun loadMoviesList(): MutableLiveData<MoviesListState> {
        launch {
            state.value = MoviesListState.LoadingState

            delay(4_000)
            val movies = moviesRepo.moviesList()
            state.value = MoviesListState.DataState(movies)
        }
        return state
    }
}
