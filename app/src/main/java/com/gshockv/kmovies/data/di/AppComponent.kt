package com.gshockv.kmovies.data.di

import com.gshockv.kmovies.MoviesApp
import com.gshockv.kmovies.presentation.di.MainActivityModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    MainActivityModule::class,
    ViewModelModule::class,
    ViewModelFactoryModule::class,
    RepositoryModule::class,
    NetworkModule::class
])
interface AppComponent {
    fun inject(app: MoviesApp)
}
