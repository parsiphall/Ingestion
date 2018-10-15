package com.example.parsiphal.ingestion.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.parsiphal.ingestion.presenter.interfaces.StartView
import java.text.SimpleDateFormat
import java.util.*

@InjectViewState
class StartPresenter : MvpPresenter<StartView>() {

    override fun attachView(view: StartView?) {
        super.attachView(view)
        setWelcomeText()
        isNewDay()
    }

    private fun isNewDay() {
        val day = SimpleDateFormat("EEEE").format(Date())
        val date = SimpleDateFormat("yyyy.MM.dd").format(Date())
        viewState.isNewDay(day, date)
    }

    private fun setWelcomeText() {
        val dayOfWeek = SimpleDateFormat("H")
        dayOfWeek.timeZone = TimeZone.getDefault()
        val hour = Integer.parseInt(dayOfWeek.format(Date()))
        viewState.setWelcome(when (hour) {
            in 5..9 -> 1
            in 10..16 -> 2
            in 17..22 -> 3
            else -> 4
        })
    }
}