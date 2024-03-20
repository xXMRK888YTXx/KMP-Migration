package com.xxmrk888ytxx.securespace

import android.app.Application
import com.xxmrk888ytxx.securespace.DI.AppComponent
import com.xxmrk888ytxx.securespace.DI.DaggerAppComponent

internal class App : Application() {

    val appComponent:AppComponent by lazy {
        DaggerAppComponent
            .factory()
            .create(applicationContext)
    }
}