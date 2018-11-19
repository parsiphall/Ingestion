package com.example.parsiphal.ingestion.view

import android.app.Application
import android.arch.persistence.room.Room
import com.example.parsiphal.ingestion.date.DataBase
import com.example.parsiphal.ingestion.date.Preferences

val prefs: Preferences by lazy {
    MainApp.prefs!!
}

class MainApp : Application() {

    companion object {
        var prefs: Preferences? = null
    }

    lateinit var mDataBase: DataBase

    override fun onCreate() {
        super.onCreate()
        prefs = Preferences(applicationContext)
        mDataBase = Room
                .databaseBuilder(applicationContext, DataBase::class.java, "ing_DB")
                .build()
    }

    fun getDataBase(): DataBase {
        return mDataBase
    }
}