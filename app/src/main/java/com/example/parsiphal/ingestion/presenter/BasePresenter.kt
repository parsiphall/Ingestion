package com.example.parsiphal.ingestion.presenter

import com.arellomobile.mvp.MvpPresenter
import com.example.parsiphal.ingestion.R
import com.example.parsiphal.ingestion.presenter.interfaces.BaseView
import java.text.SimpleDateFormat
import java.util.*

abstract class BasePresenter<T, V : BaseView> : MvpPresenter<V>() {

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