package com.gshockv.kmovies

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gshockv.kmovies.data.MoviesDownloader
import com.gshockv.kmovies.data.MoviesRepository
import com.gshockv.kmovies.data.api.ApiResult
import com.gshockv.kmovies.data.api.MoviesDbApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class MoviesRepositoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repo: MoviesRepository
    private val moviesApi: MoviesDbApi = mockk()

    @Before
    fun setup() {
        repo = MoviesDownloader(moviesApi)
    }

    @Test
    fun `Load Movies List Positive`() = runBlocking {
        coEvery {
            moviesApi.discoverMovies()
        } returns GlobalScope.async {
            Response.success(TestData.testSuccessfullApiResult)
        }

        val apiResult = repo.discoverMovies()

        coVerify {
            moviesApi.discoverMovies()
        }

        Assert.assertTrue(apiResult is ApiResult.Success)
        val success = apiResult as ApiResult.Success
        Assert.assertEquals(TestData.testMovieSize, success.data.movies.size)
    }
}