package com.gshockv.kmovies

import android.app.Activity
import android.app.Application
import com.gshockv.kmovies.data.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MoviesApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.create().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector
}
