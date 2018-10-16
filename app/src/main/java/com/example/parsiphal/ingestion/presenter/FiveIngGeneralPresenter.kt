package com.example.parsiphal.ingestion.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.parsiphal.ingestion.model.FiveIngModel
import com.example.parsiphal.ingestion.presenter.interfaces.GeneralView
import com.example.parsiphal.ingestion.view.prefs
import java.text.SimpleDateFormat
import java.util.*

@InjectViewState
class FiveIngGeneralPresenter : BasePresenter<FiveIngModel, GeneralView>() {

    override fun attachView(view: GeneralView?) {
        super.attachView(view)

        if (prefs.newDay!!) {
            calculateNextFeedTimeFirst()
        } else {
            calculateNextFeedTimeAuto()
        }
    }

    private fun calculateNextFeedTimeFirst() {
        Log.d("qwe", "calculateNextFeedTimeFirst")
        val hour = getHour()
        val minute = getMinute()
        if (minute >= 30) {
            prefs.nextFeedTimeHour = hour + 1
            prefs.nextFeedTimeMinute = minute - 30
        } else {
            prefs.nextFeedTimeHour = hour
            prefs.nextFeedTimeMinute = minute + 30
        }
        setNextIngestion()
        setNowIngestion()
    }

    fun calculateNextFeedTimeManual() {
        Log.d("qwe", "calculateNextFeedTimeManual")
        val hour = getHour()
        val minute = getMinute()
        when (prefs.feedNumber) {
            1, 3 -> {
                if (minute >= 30) {
                    prefs.nextFeedTimeHour = hour + 4
                    prefs.nextFeedTimeMinute = minute - 30
                } else {
                    prefs.nextFeedTimeHour = hour + 3
                    prefs.nextFeedTimeMinute = minute + 30
                }
            }
            2, 4 -> {
                prefs.nextFeedTimeHour = hour + 3
            }
            else -> prefs.nextFeedTimeHour = 600
        }
        prefs.feedNumber = prefs.feedNumber?.plus(1)
        setNextIngestion()
        viewState.nowIngestion(0)
    }

    private fun calculateNextFeedTimeAuto() {
        Log.d("qwe", "calculateNextFeedTimeAuto")
        val hour = getHour()
        val minute = getMinute()
        if (hour > prefs.nextFeedTimeHour!!) {
            prefs.nextFeedTimeHour = hour + 2
            prefs.nextFeedTimeMinute = minute
            prefs.feedNumber = prefs.feedNumber?.plus(1)
        }
        setNextIngestion()
        setNowIngestion()
    }

    fun calculateNextFeedTimePass() {
        Log.d("qwe", "calculateNextFeedTimePass")
        val hour = getHour()
        val minute = getMinute()
        prefs.nextFeedTimeHour = hour + 2
        prefs.nextFeedTimeMinute = minute
        prefs.feedNumber = prefs.feedNumber?.plus(1)
        setNextIngestion()
        setNowIngestion()
    }

    private fun setNextIngestion() {
        viewState.nextIngestion(prefs.nextFeedTimeHour!!, prefs.nextFeedTimeMinute!!)
    }

    private fun setNowIngestion() {
        viewState.nowIngestion(prefs.feedNumber!!)
    }

    private fun getHour(): Int {
        val nowHourSDF = SimpleDateFormat("HH")
        nowHourSDF.timeZone = TimeZone.getDefault()
        return Integer.parseInt(nowHourSDF.format(Date()))
    }

    private fun getMinute(): Int {
        val nowMinuteSDF = SimpleDateFormat("mm")
        return Integer.parseInt(nowMinuteSDF.format(Date()))
    }
}