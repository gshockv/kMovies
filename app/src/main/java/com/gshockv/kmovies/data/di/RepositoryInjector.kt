package com.gshockv.kmovies.data.di

import com.gshockv.kmovies.data.MoviesRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface RepositoryInjector {

    fun inject(moviesRepository: MoviesRepository)

    @Component.Builder
    interface Builder {
        fun build() : RepositoryInjector

        fun networkModule(networkModule: NetworkModule) : Builder
    }
}
