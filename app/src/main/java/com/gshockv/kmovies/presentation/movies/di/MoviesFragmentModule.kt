package com.gshockv.kmovies.presentation.movies.di

import com.gshockv.kmovies.presentation.movies.MoviesFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [MoviesFragmentSubComponent::class])
abstract class MoviesFragmentModule {
    @Binds
    @IntoMap
    @ClassKey(MoviesFragment::class)
    abstract fun bindMoviesFragmentInjectorFactory(builder: MoviesFragmentSubComponent.Builder)
            : AndroidInjector.Factory<*>
}
