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
        val day = SimpleDateFormat("EEEE").format(Date())
        viewState.isNewWeek(day)
    }

    private fun setWelcomeText() {
        val time = SimpleDateFormat("H")
        time.timeZone = TimeZone.getDefault()
        val hour = Integer.parseInt(time.format(Date()))
        viewState.setWelcome(when (hour) {
            in 5..9 -> 1
            in 10..16 -> 2
            in 17..22 -> 3
            else -> 4
        })
    }
}