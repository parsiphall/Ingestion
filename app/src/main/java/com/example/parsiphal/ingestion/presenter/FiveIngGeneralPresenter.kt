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

    private var notifyTime: Long = 0
//    private var notifyTimeTest: Long = 10000

    override fun attachView(view: GeneralView?) {
        super.attachView(view)

        if (prefs.newDay!! == 0) {
            prefs.newDay = 1
            calculateNextFeedTimeFirst()
        } else {
            calculateNextFeedTimeAuto()
        }
    }

    private fun calculateNextFeedTimeFirst() {
        Log.d("qwe", "calculateNextFeedTimeFirst")
        val hour = getHour()
        val minute = getMinute()
        prefs.nextFeedTimeHour = hour + 1
        prefs.nextFeedTimeMinute = minute
        notifyTime = 1800000L
        viewState.notification(notifyTime)
        setNextIngestion()
        setNowIngestion()
    }

    fun calculateNextFeedTimeManual() {
        Log.d("qwe", "calculateNextFeedTimeManual")
        var hour = getHour()
        val minute = getMinute()
        when (prefs.feedNumber) {
            0, 2 -> {
                if (minute >= 30) {
                    if (hour >= 20) hour -= 24
                    prefs.nextFeedTimeHour = hour + 4
                    prefs.nextFeedTimeMinute = minute - 30
                } else {
                    if (hour >= 21) hour -= 24
                    prefs.nextFeedTimeHour = hour + 3
                    prefs.nextFeedTimeMinute = minute + 30
                }
                notifyTime = 10800000L
            }
            1, 3 -> {
                prefs.nextFeedTimeHour = hour + 3
                notifyTime = 9000000L
            }
            else -> {
                prefs.nextFeedTimeHour = 600
                notifyTime = 0L
            }
        }
        prefs.feedNumber = prefs.feedNumber?.plus(1)
        viewState.nowIngestion(0)
        viewState.notification(notifyTime)
    }

    private fun calculateNextFeedTimeAuto() {
        Log.d("qwe", "calculateNextFeedTimeAuto")
        var hour = getHour()
        if (hour >= 22) hour -= 24
        val minute = getMinute()
        if (hour > prefs.nextFeedTimeHour!! || (hour == prefs.nextFeedTimeHour!! && minute > prefs.nextFeedTimeMinute!!)) {
            prefs.nextFeedTimeHour = hour + 2
            prefs.nextFeedTimeMinute = minute
            prefs.feedNumber = prefs.feedNumber?.plus(1)
        }
        setNextIngestion()
        setNowIngestion()
    }

    fun calculateNextFeedTimePass() {
        Log.d("qwe", "calculateNextFeedTimePass")
        var hour = getHour()
        if (hour >= 22) hour -= 24
        val minute = getMinute()
        notifyTime = 5400000L
        prefs.nextFeedTimeHour = prefs.nextFeedTimeHour?.plus(2)
        prefs.nextFeedTimeMinute = minute
        prefs.feedNumber = prefs.feedNumber?.plus(1)
        viewState.notification(notifyTime)
    }

    fun setNextIngestion() {
        viewState.nextIngestion(prefs.nextFeedTimeHour!!, prefs.nextFeedTimeMinute!!)
    }

    fun setNowIngestion() {
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