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

    private var plusDay: Boolean = false
//    private var notifyTimeTest: Long = 10000

    override fun attachView(view: GeneralView?) {
        super.attachView(view)

        if (prefs.newDay!! == 0) {
            prefs.newDay = 1
            calculateNextFeedTimeFirst()
        } else {
            setNextIngestion()
            setNowIngestion()
        }
    }

    private fun calculateNextFeedTimeFirst() {
        Log.d("qwe", "calculateNextFeedTimeFirst")
        var hour = getHour()
        val minute = getMinute()
        if (hour >= 23) {
            hour -= 24
            plusDay = true
        }
        prefs.nextFeedTimeHour = hour + 1
        prefs.nextFeedTimeMinute = minute
        viewState.notification(plusDay)
        setNextIngestion()
        setNowIngestion()
    }

    fun calculateNextFeedTimeManual() {
        Log.d("qwe", "calculateNextFeedTimeManual")
        var hour = getHour()
        val minute = getMinute()
        viewState.storeToBase(hour, minute, prefs.feedNumber!!, true)
        when (prefs.feedNumber) {
            0, 2 -> {
                if (minute >= 30) {
                    if (hour >= 20) {
                        hour -= 24
                        plusDay = true
                    }
                    prefs.nextFeedTimeHour = hour + 4
                    prefs.nextFeedTimeMinute = minute - 30
                } else {
                    if (hour >= 21) {
                        hour -= 24
                        plusDay = true
                    }
                    prefs.nextFeedTimeHour = hour + 3
                    prefs.nextFeedTimeMinute = minute + 30
                }
            }
            1, 3 -> {
                if (hour >= 21) {
                    hour -= 24
                    plusDay = true
                }
                prefs.nextFeedTimeHour = hour + 3
            }
            else -> {
                prefs.nextFeedTimeHour = 600
            }
        }
        prefs.feedNumber = prefs.feedNumber?.plus(1)
        viewState.notification(plusDay)
    }

    private fun calculateNextFeedTimeAuto() {
        Log.d("qwe", "calculateNextFeedTimeAuto")
        var hour = getHour()
        if (hour >= 22) {
            hour -= 24
            plusDay = true
        }
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
        if (hour >= 22) {
            hour -= 24
            plusDay = true
        }
        val minute = getMinute()
        viewState.storeToBase(0,0, prefs.feedNumber!!,false)
        when (prefs.feedNumber) {
            0, 1, 2, 3, 4 -> {
                prefs.nextFeedTimeHour = hour + 2
                prefs.nextFeedTimeMinute = minute
            }
            else -> {
                prefs.nextFeedTimeHour = 600
            }
        }
        prefs.feedNumber = prefs.feedNumber?.plus(1)
        viewState.notification(plusDay)
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