package com.example.parsiphal.ingestion.view

import android.app.Application
import com.example.parsiphal.ingestion.date.Preferences

val prefs: Preferences by lazy {
    MainApp.prefs!!
}

class MainApp: Application() {

    companion object {
        var prefs: Preferences? = null
    }

    override fun onCreate() {
        prefs = Preferences(applicationContext)
        super.onCreate()
    }
}