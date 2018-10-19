package com.example.parsiphal.ingestion.alerts

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.media.RingtoneManager
import android.support.v4.app.NotificationCompat
import com.example.parsiphal.ingestion.R
import com.example.parsiphal.ingestion.presenter.FiveIngGeneralPresenter
import com.example.parsiphal.ingestion.view.MainActivity
import com.example.parsiphal.ingestion.view.prefs

class MyNotification : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val nm = context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val launchIntent = Intent(context, MainActivity::class.java)
        val pIntent = PendingIntent.getActivity(context, 0, launchIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val nb = NotificationCompat.Builder(context)
        val notifyTime = 1800000L
        val notifyTimeToNext = 5400000L
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //val notifyTimeTest = 5000L
    ////////////////////////////////////////////////////////////////////////////////////////////////
        when (prefs.notifyFlag) {
            1 -> {
                nb.setContentTitle(context.getString(R.string.notify_soon_ing))
                        .setContentText(chooseIng(context))
                prefs.notifyFlag = prefs.notifyFlag?.plus(1)
                notification(context, notifyTime)
            }
            2 -> {
                nb.setContentTitle(context.getString(R.string.notify_now_ing))
                        .setContentText(chooseIng(context))
                prefs.notifyFlag = prefs.notifyFlag?.plus(1)
                notification(context, notifyTime)
            }
            3 -> {
                nb.setContentTitle(context.getString(R.string.notify_passed_ing))
                        .setContentText(chooseIng(context))
                prefs.notifyFlag = 1
                FiveIngGeneralPresenter().calculateNextFeedTimePass()
                if (prefs.feedNumber!! < 5) {
                    notification(context, notifyTimeToNext)
                }
            }
        }
        nb.setSmallIcon(R.drawable.ic_launcher_round)
                .setContentIntent(pIntent)
                .setSound(alarmSound)
                .setVibrate(longArrayOf(0, 400, 300, 1000))
                .setAutoCancel(true)
        nm.notify(0, nb.build())
    }

    private fun notification(context: Context, notifyTime: Long) {
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.set(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + notifyTime,
                PendingIntent.getBroadcast(
                        context,
                        0,
                        Intent(context, MyNotification::class.java),
                        PendingIntent.FLAG_UPDATE_CURRENT
                )
        )
    }

    private fun chooseIng(context: Context): String {
        return when (prefs.feedNumber) {
            0 -> context.getString(R.string.general_breakfast)
            1 -> context.getString(R.string.general_snack1)
            2 -> context.getString(R.string.general_lunch)
            3 -> context.getString(R.string.general_snack2)
            4 -> context.getString(R.string.general_dinner)
            else -> context.getString(R.string.error)
        }
    }
}