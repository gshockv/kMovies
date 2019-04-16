package com.gshockv.kmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gshockv.kmovies.data.MoviesRepository
import com.gshockv.kmovies.data.api.ApiResult
import com.gshockv.kmovies.data.domain.DiscoverMoviesInteractor
import com.gshockv.kmovies.data.domain.DiscoverMoviesUseCase
import com.gshockv.kmovies.presentation.movies.MoviesViewState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MoviesUseCaseTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var useCase: DiscoverMoviesUseCase
    private val repo : MoviesRepository = mockk()

    @Before
    fun setup() {
        useCase = DiscoverMoviesInteractor(repo)
    }

    @Test
    fun `Load Movies List Positive`() = runBlocking {
        prepareSuccessTestData()

        val state = useCase.discoverMovies()

        coVerify {
            repo.discoverMovies()
        }

        Assert.assertTrue(state is MoviesViewState.DataState)

        val response = (state as MoviesViewState.DataState).data
        Assert.assertEquals(TestData.FIRST_PAGE, response.page)
        Assert.assertEquals(TestData.testMovieSize, response.movies.size)
    }

    @Test
    fun `Load Movies List Negative`() = runBlocking {
        prepareFailureTestData()

        val state = useCase.discoverMovies()

        coVerify {
            repo.discoverMovies()
        }

        Assert.assertTrue(state is MoviesViewState.ErrorState)
    }

    private fun prepareSuccessTestData() {
        coEvery {
            repo.discoverMovies()
        } returns ApiResult.Success(TestData.testSuccessfullApiResult)
    }

    private fun prepareFailureTestData() {
        coEvery {
            repo.discoverMovies()
        } returns TestData.testFailureApiResult
    }
}