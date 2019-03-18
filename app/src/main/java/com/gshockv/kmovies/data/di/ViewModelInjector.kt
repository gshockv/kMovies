package com.gshockv.kmovies.data.di

import com.gshockv.kmovies.ui.movies.MoviesListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface ViewModelInjector {

    fun inject(moviesListViewModel: MoviesListViewModel)

    @Component.Builder
    interface Builder {
        fun build() : ViewModelInjector

        fun repositoryModule(repositoryModule: RepositoryModule) : Builder
    }
}
