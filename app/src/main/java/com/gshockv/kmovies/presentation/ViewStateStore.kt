package com.gshockv.kmovies.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class ViewStateStore<T : Any>(initialState: T) {

    val liveData = MutableLiveData<T>().apply {
        value = initialState
    }

    fun observe(owner: LifecycleOwner, observer: (T) -> Unit) =
            liveData.observe(owner, Observer { observer(it!!) })

    fun dispatchState(state: T) {
        liveData.value = state
    }

    fun state() = liveData.value!!
}
