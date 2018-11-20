package com.example.parsiphal.ingestion.view

import android.app.Application
import android.arch.persistence.room.Room
import com.example.parsiphal.ingestion.date.DataBase
import com.example.parsiphal.ingestion.date.Preferences

val prefs: Preferences by lazy {
    MainApp.prefs!!
}

val DB: DataBase by lazy {
    MainApp.mDataBase!!
}

class MainApp : Application() {

    companion object {
        var prefs: Preferences? = null
        var mDataBase: DataBase? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Preferences(applicationContext)
        mDataBase = Room
                .databaseBuilder(applicationContext, DataBase::class.java, "ing_DB")
                .allowMainThreadQueries()
                .build()
    }

    fun getDataBase(): DataBase {
        return mDataBase!!
    }
}