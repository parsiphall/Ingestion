package com.example.parsiphal.ingestion.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.parsiphal.ingestion.alerts.MyNotification
import com.example.parsiphal.ingestion.R
import com.example.parsiphal.ingestion.presenter.FiveIngGeneralPresenter
import com.example.parsiphal.ingestion.presenter.interfaces.GeneralView
import com.example.parsiphal.ingestion.presenter.interfaces.MainView
import kotlinx.android.synthetic.main.fragment_general.*
import java.text.MessageFormat

//TODO Логика расписания при помощи sheduler
//TODO Запись в DB. Транслирование в MainActivity(Room LiveData?)

class GeneralFragment : MvpAppCompatFragment(), GeneralView {


    @InjectPresenter
    lateinit var fiveIngGeneralPresenter: FiveIngGeneralPresenter
    lateinit var callBackActivity: MainView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callBackActivity = context as MainView
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_general, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var drinkCount: Int = prefs.drinkCount!!
        setDrinkCount(drinkCount)

        general_drink_button_add.setOnClickListener {
            animate(general_drink_button_add)
            drinkCount++
            setDrinkCount(drinkCount)
        }

        general_drink_button_remove.setOnClickListener {
            animate(general_drink_button_remove)
            if (drinkCount > 0) {
                drinkCount--
                setDrinkCount(drinkCount)
            }
        }

        general_eat_button.setOnClickListener {
            animate(general_eat_button)
            prefs.notifyFlag = 1
            fiveIngGeneralPresenter.calculateNextFeedTimeManual()
            fiveIngGeneralPresenter.setNextIngestion()
            general_now_ingestion.text = getString(R.string.general_bon_appetite)
        }

        general_pass_button.setOnClickListener {
            animate(general_pass_button)
            prefs.notifyFlag = 1
            fiveIngGeneralPresenter.calculateNextFeedTimePass()
            fiveIngGeneralPresenter.setNextIngestion()
            fiveIngGeneralPresenter.setNowIngestion()
        }
    }

    override fun notification(notifyTime: Long) {
        val context = activity!!.applicationContext
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

    private fun setDrinkCount(drinkCount: Int) {
        val count = MessageFormat.format(resources.getString(R.string.general_water_count), drinkCount)
        general_drink_count_textView.text = count
        prefs.drinkCount = drinkCount
        callBackActivity.setWater(count)
    }


    override fun nextIngestion(nowHour: Int, nowMinute: Int) {
        if (nowHour == 600) {
            general_next_ingestion.text = getString(R.string.general_next_ingestion_tomorrow)
        } else {
            general_next_ingestion.text = resources.getString(R.string.general_next_ingestion, nowHour, nowMinute)
        }
    }

    override fun nowIngestion(feedNumber: Int) {
        val nowIngestion = when (feedNumber) {
            0 -> resources.getString(R.string.general_breakfast)
            1 -> resources.getString(R.string.general_snack1)
            2 -> resources.getString(R.string.general_lunch)
            3 -> resources.getString(R.string.general_snack2)
            4 -> resources.getString(R.string.general_dinner)
            else -> resources.getString(R.string.general_sleep)
        }
        general_now_ingestion.text = MessageFormat.format(resources.getString(R.string.general_now_ingestion), nowIngestion)
    }

    override fun setWater(water: String) {
        general_drink_count_textView.text = water
    }

    private fun animate(view: View) {
        YoYo.with(Techniques.Landing)
                .duration(300)
                .repeat(0)
                .playOn(view)
    }
}
