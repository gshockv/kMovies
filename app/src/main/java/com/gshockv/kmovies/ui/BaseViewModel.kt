package com.gshockv.kmovies.ui

import androidx.lifecycle.ViewModel
import com.gshockv.kmovies.data.di.DaggerViewModelInjector
import com.gshockv.kmovies.data.di.RepositoryModule
import com.gshockv.kmovies.data.di.ViewModelInjector
import com.gshockv.kmovies.ui.movies.MoviesListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val injector : ViewModelInjector = DaggerViewModelInjector
        .builder()
        .repositoryModule(RepositoryModule)
        .build()

    init {
        injectDependencies()
    }

    private fun injectDependencies() {
        when (this) {
            is MoviesListViewModel -> injector.inject(this)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
