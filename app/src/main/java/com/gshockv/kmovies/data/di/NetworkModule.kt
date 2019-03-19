package com.gshockv.kmovies.data.di

import com.gshockv.kmovies.BuildConfig
import com.gshockv.kmovies.data.api.MoviesDbApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {
    const val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    @Reusable
    fun provideMoviesApi(retrofit: Retrofit) : MoviesDbApi = retrofit.create(MoviesDbApi::class.java)

    @Provides
    @Reusable
    @JvmStatic
    fun provideRetrofitInstance(httpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient)
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    fun provideHttpClient() : OkHttpClient {
        val apiKey = BuildConfig.API_KEY

        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor {
            var request = it.request()
            val url = request.url().newBuilder().addQueryParameter("api_key", apiKey).build()
            request = request.newBuilder().url(url).build()
            it.proceed(request)
        }
        clientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        return clientBuilder.build()
    }
}
