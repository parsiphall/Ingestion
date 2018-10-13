package com.example.parsiphal.ingestion.presenter

import java.text.SimpleDateFormat
import java.util.*
import com.example.parsiphal.ingestion.R

class MainPresenter {
    fun veryFirstLaunch() {

    }

    fun getDate(): String {
        return SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH).format(Date())
    }

    fun isNewDay(): Boolean {
        return true
    }

    fun isNewWeek(): Boolean {
        return (SimpleDateFormat("EEEE", Locale.ENGLISH).format(Date())) == R.string.welcome_monday.toString()
    }
}