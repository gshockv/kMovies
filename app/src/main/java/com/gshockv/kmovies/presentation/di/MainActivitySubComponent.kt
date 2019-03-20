package com.gshockv.kmovies.presentation.di

import com.gshockv.kmovies.presentation.MainActivity
import com.gshockv.kmovies.presentation.movies.di.MoviesFragmentModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [MoviesFragmentModule::class])
interface MainActivitySubComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}
