package com.gshockv.kmovies.data.di

import com.gshockv.kmovies.data.api.MoviesDbApi
import com.gshockv.kmovies.data.model.ApiKey
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    private val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    fun provideMoviesApi(retrofit: Retrofit) : MoviesDbApi = retrofit.create(MoviesDbApi::class.java)

    @Provides
    fun provideRetrofitInstance(httpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(httpClient)
            .build()
    }

    @Provides
    fun provideHttpClient(apiKey: ApiKey) : OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor {
            var request = it.request()
            val url = request.url().newBuilder().addQueryParameter("api_key", apiKey.value).build()
            request = request.newBuilder().url(url).build()
            it.proceed(request)
        }
        clientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        return clientBuilder.build()
    }
}
