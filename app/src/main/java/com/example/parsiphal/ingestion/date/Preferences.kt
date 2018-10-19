package com.example.parsiphal.ingestion.date

import android.content.Context
import android.content.SharedPreferences
import com.example.parsiphal.ingestion.R

class Preferences(context: Context) {
    val PREFS_FILENAME = "com.example.parsiphal.ingestion.prefs"
    val LAST_USE_DATE = "last_use_day"
    val THIS_WEEK_WEIGHT = "this_week_weight"
    val DRINK_COUNT = "drink_count"
    val START_NEW_DAY_HOUR = "start_new_day_hour"
    val FEED_NUMBER = "feed_number"
    val NEXT_FEED_TIME_HOUR = "next_feed_time_hour"
    val NEXT_FEED_TIME_MINUTE = "next_feed_time_minute"
    val NEW_DAY = "new_day"
    val NOTIFY_FLAG = "notify_flag"
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

    var startNewDayHour: Int?
        get() = prefs.getInt(START_NEW_DAY_HOUR, 8)
        set(value) = prefs.edit().putInt(START_NEW_DAY_HOUR, value!!).apply()

    var feedNumber: Int?
        get() = prefs.getInt(FEED_NUMBER, 0)
        set(value) = prefs.edit().putInt(FEED_NUMBER, value!!).apply()

    var nextFeedTimeHour: Int?
        get() = prefs.getInt(NEXT_FEED_TIME_HOUR, 0)
        set(value) = prefs.edit().putInt(NEXT_FEED_TIME_HOUR, value!!).apply()

    var nextFeedTimeMinute: Int?
        get() = prefs.getInt(NEXT_FEED_TIME_MINUTE, 0)
        set(value) = prefs.edit().putInt(NEXT_FEED_TIME_MINUTE, value!!).apply()

    var newDay: Int?
        get() = prefs.getInt(NEW_DAY, 0)
        set(value) = prefs.edit().putInt(NEW_DAY, value!!).apply()

    var notifyFlag: Int?
        get() = prefs.getInt(NOTIFY_FLAG, 0)
        set(value) = prefs.edit().putInt(NOTIFY_FLAG, value!!).apply()
}