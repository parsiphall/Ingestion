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
        isNewWeek()
    }

    private fun isNewWeek() {
        val day = SimpleDateFormat("EEEE", Locale.ENGLISH).format(Date())
        viewState.isNewWeek(day)
    }

    private fun setWelcomeText() {
        val sdfHour = SimpleDateFormat("H")
        sdfHour.timeZone = TimeZone.getDefault()
        val hour = Integer.parseInt(sdfHour.format(Date()))
        when (hour) {
            in 5..9 -> viewState.setWelcome(1)
            in 10..16 -> viewState.setWelcome(2)
            in 17..22 -> viewState.setWelcome(3)
            else -> viewState.setWelcome(4)
        }
    }
}