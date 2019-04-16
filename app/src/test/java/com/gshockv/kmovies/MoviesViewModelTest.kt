package com.gshockv.kmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.gshockv.kmovies.data.domain.DiscoverMoviesUseCase
import com.gshockv.kmovies.data.model.MoviesResponse
import com.gshockv.kmovies.presentation.movies.MoviesViewModel
import com.gshockv.kmovies.presentation.movies.MoviesViewState
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MoviesViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = Dispatchers.Unconfined

    private val useCase: DiscoverMoviesUseCase = mockk()
    private lateinit var viewModel: MoviesViewModel

    private lateinit var observer: Observer<MoviesViewState>

    @Before
    fun setup() {
        viewModel = MoviesViewModel(dispatcher, useCase)
        observer = mockk(relaxed = true)
    }

    @Test
    fun `Call UseCase With Loading State`() {
        coEvery {
            useCase.discoverMovies()
        } returns MoviesViewState.LoadingState

        viewModel.loadMoviesList()

        // Do we really need to check this call?
        coVerify { useCase.discoverMovies() }

        val expectedState = MoviesViewState.LoadingState
        assert(viewModel.store.state() == expectedState)
    }

    @Test
    fun `Call UseCase With Error State`() {
        val errorState = MoviesViewState.ErrorState(Throwable("Error State"))
        coEvery {
            useCase.discoverMovies()
        } returns errorState

        viewModel.loadMoviesList()

        // Do we really need to check this call?
        coVerify { useCase.discoverMovies() }

        assert(viewModel.store.state() == errorState)
    }

    @Test
    fun `Call UseCase With Data State`() {
        val dataState = MoviesViewState.DataState(MoviesResponse())
        coEvery {
            useCase.discoverMovies()
        } returns dataState

        viewModel.store.liveData.observeForever(observer)
        viewModel.loadMoviesList()

        // Do we really need to check this call?
        coVerify { useCase.discoverMovies() }

        verifyOrder {
            observer.onChanged(MoviesViewState.LoadingState)
            observer.onChanged(dataState)
        }
        confirmVerified(observer)

        assert(viewModel.store.state() == dataState)
    }
}