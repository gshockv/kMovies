package com.gshockv.kmovies.presentation.movies.di

import com.gshockv.kmovies.presentation.movies.MoviesFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface MoviesFragmentSubComponent : AndroidInjector<MoviesFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MoviesFragment>()
}
