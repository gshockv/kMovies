package com.gshockv.kmovies.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

//    private val injector : ViewModelInjector = DaggerViewModelInjector
//        .builder()
//        .repositoryModule(RepositoryModule)
//        .build()
//
//    init {
//        injectDependencies()
//    }
//
//    private fun injectDependencies() {
//        when (this) {
//            is MoviesViewModel -> injector.inject(this)
//        }
//    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
