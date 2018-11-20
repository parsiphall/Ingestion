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

        val nowHour = SimpleDateFormat("H")
        val day = SimpleDateFormat("EEEE").format(Date())
        val date = SimpleDateFormat("dd/MM/yyyy").format(Date())

        setWelcomeText(nowHour)
        isNewDay(day, date, nowHour)
    }

    private fun isNewDay(day: String, date: String, nowHour: SimpleDateFormat) {
        nowHour.timeZone = TimeZone.getDefault()
        val hour = Integer.parseInt(nowHour.format(Date()))
        viewState.isNewDay(day, date, hour)
    }

    private fun setWelcomeText(nowHour: SimpleDateFormat) {
        nowHour.timeZone = TimeZone.getDefault()
        val hour = Integer.parseInt(nowHour.format(Date()))
        viewState.setWelcome(when (hour) {
            in 5..9 -> 1
            in 10..16 -> 2
            in 17..22 -> 3
            else -> 4
        })
    }
}