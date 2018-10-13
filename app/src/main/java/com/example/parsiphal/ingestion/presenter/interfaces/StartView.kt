package com.example.parsiphal.ingestion.presenter.interfaces

interface StartView : BaseView {

    fun setWelcome(welcome: Int)
    fun isNewWeek(day: String)
}