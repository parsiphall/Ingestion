package com.example.parsiphal.ingestion.date

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {
    val PREFS_FILENAME = "com.example.parsiphal.ingestion.prefs"
    val LAST_USE_DATE = "last_use_day"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var lastUseDay: String?
        get() = prefs.getString(LAST_USE_DATE, "")
        set(value) = prefs.edit().putString(LAST_USE_DATE, value).apply()
}