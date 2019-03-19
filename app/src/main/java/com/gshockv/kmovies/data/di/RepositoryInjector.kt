package com.gshockv.kmovies.data.di

import com.gshockv.kmovies.data.MoviesDownloader
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface RepositoryInjector {

    fun inject(moviesRepository: MoviesDownloader)

    @Component.Builder
    interface Builder {
        fun build() : RepositoryInjector

        fun networkModule(networkModule: NetworkModule) : Builder
    }
}
