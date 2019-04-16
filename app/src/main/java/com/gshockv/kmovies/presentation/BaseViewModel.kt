package com.gshockv.kmovies.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(dispatcher: CoroutineDispatcher) : ViewModel() {

    private val job = Job()

    val uiScope = CoroutineScope(dispatcher + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
