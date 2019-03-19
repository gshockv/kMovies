package com.gshockv.kmovies.ui.movies

import androidx.lifecycle.MediatorLiveData
import com.gshockv.kmovies.data.MoviesRepository
import com.gshockv.kmovies.data.api.ApiResult
import com.gshockv.kmovies.ui.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesListViewModel : BaseViewModel() {

    companion object {
        const val TAG = "MoviesListViewModel"
    }

    val state = MediatorLiveData<MoviesUiState>()

    @Inject
    lateinit var moviesRepo : MoviesRepository

    fun loadMoviesList() {
        state.postValue(MoviesUiState.LoadingState)

        launch {
            val result = moviesRepo.discoverMovies()
            when (result) {
                is ApiResult.Success -> state.postValue(MoviesUiState.DataState(result.data))
                is ApiResult.Error -> state.postValue(MoviesUiState.ErrorState(result.exception.message!!))
            }
        }
    }
}
