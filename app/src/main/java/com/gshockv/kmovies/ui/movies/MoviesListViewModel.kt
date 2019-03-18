package com.gshockv.kmovies.ui.movies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gshockv.kmovies.data.MoviesRepository
import com.gshockv.kmovies.ui.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesListViewModel : BaseViewModel() {

    companion object {
        const val TAG = "MoviesListViewModel"
    }

    private val state = MutableLiveData<MoviesListState>()

    @Inject
    lateinit var moviesRepo : MoviesRepository

    fun loadMoviesList(): MutableLiveData<MoviesListState> {
        launch {
            state.value = MoviesListState.LoadingState

            delay(4_000)
            val movies = moviesRepo.moviesList()

            Log.d(TAG, "Movies count: ${movies.movies.size}")

            state.value = MoviesListState.DataState(movies)
        }
        return state
    }
}
