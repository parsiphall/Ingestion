package com.example.parsiphal.ingestion.date

import android.content.Context
import android.content.SharedPreferences
import com.example.parsiphal.ingestion.R

class Preferences(context: Context) {
    val PREFS_FILENAME = "com.example.parsiphal.ingestion.prefs"
    val LAST_USE_DATE = "last_use_day"
    val THIS_WEEK_WEIGHT = "this_week_weight"
    val DRINK_COUNT = "drink_count"
    val NO_DATA = context.getString(R.string.no_data)
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var lastUseDay: String?
        get() = prefs.getString(LAST_USE_DATE, "")
        set(value) = prefs.edit().putString(LAST_USE_DATE, value).apply()

    var thisWeekWeight: String?
        get() = prefs.getString(THIS_WEEK_WEIGHT, NO_DATA)
        set(value) = prefs.edit().putString(THIS_WEEK_WEIGHT, value).apply()

    var drinkCount: Int?
        get() = prefs.getInt(DRINK_COUNT, 0)
        set(value) = prefs.edit().putInt(DRINK_COUNT, value!!).apply()
}