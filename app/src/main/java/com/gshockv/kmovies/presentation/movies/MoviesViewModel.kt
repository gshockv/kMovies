package com.gshockv.kmovies.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.gshockv.kmovies.data.MoviesRepository
import com.gshockv.kmovies.data.api.ApiResult
import com.gshockv.kmovies.presentation.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val moviesRepo: MoviesRepository)
    : BaseViewModel() {

    companion object {
        const val TAG = "MoviesViewModel"
    }

    private val _state = MediatorLiveData<MoviesUiState>()
    val uiState : LiveData<MoviesUiState>
        get() = _state

    fun loadMoviesList() {
        _state.postValue(MoviesUiState.LoadingState)

        launch {
            val result = moviesRepo.discoverMovies()
            when (result) {
                is ApiResult.Success -> _state.postValue(MoviesUiState.DataState(result.data))
                is ApiResult.Error -> _state.postValue(MoviesUiState.ErrorState(result.exception.message!!))
            }
        }
    }
}
