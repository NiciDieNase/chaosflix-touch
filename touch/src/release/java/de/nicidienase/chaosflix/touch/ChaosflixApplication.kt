package de.nicidienase.chaosflix.touch

import android.app.Application
import android.content.Context

class ChaosflixApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        APPLICATION_CONTEXT = this
    }

    companion object {
        lateinit var APPLICATION_CONTEXT: Context
    }
}
