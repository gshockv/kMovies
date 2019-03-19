package com.gshockv.kmovies.data

import android.util.Log
import com.gshockv.kmovies.data.api.ApiResult
import com.gshockv.kmovies.data.api.MoviesDbApi
import com.gshockv.kmovies.data.di.DaggerRepositoryInjector
import com.gshockv.kmovies.data.di.NetworkModule
import com.gshockv.kmovies.data.di.RepositoryInjector
import com.gshockv.kmovies.data.model.MoviesList
import java.io.IOException
import javax.inject.Inject

class MoviesRepository {
    companion object {
        const val TAG = "MoviesRepository"
    }

    private val injector: RepositoryInjector = DaggerRepositoryInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        injector.inject(this)
    }

    @Inject
    lateinit var moviesApi : MoviesDbApi

    suspend fun discoverMovies() = safeApiCall(
        call = { callDiscoverMovies() },
        errorMessage = "Error Occurred"
    )

    private suspend fun callDiscoverMovies() : ApiResult<MoviesList> {
        val response = moviesApi.discoverMovies().await()
        if (response.isSuccessful) {
            Log.d(TAG, "Response: ${response.body().toString()}")
            return ApiResult.Success(response.body()!!)
        }
        return ApiResult.Error(IOException("Error occurred during fetching movies."))
    }

    private suspend fun <T : Any> safeApiCall(
        call : suspend () -> ApiResult<T>,
        errorMessage: String
    ): ApiResult<T> = try {
        call.invoke()
    } catch (ex: Exception) {
        ApiResult.Error(IOException(errorMessage, ex))
    }
}
