package com.example.parsiphal.ingestion.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.parsiphal.ingestion.R
import com.example.parsiphal.ingestion.presenter.interfaces.GeneralView
import com.example.parsiphal.ingestion.presenter.interfaces.MainView
import kotlinx.android.synthetic.main.fragment_general.*
import java.text.MessageFormat

//TODO Логика расписания при помощи sheduler
//TODO Запись в DB. Транслирование в MainActivity(Room LiveData?)

class GeneralFragment : MvpAppCompatFragment(), GeneralView {

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
            YoYo.with(Techniques.Landing)
                        .duration(300)
                        .repeat(0)
                        .playOn(general_drink_button_add)
            drinkCount++
            setDrinkCount(drinkCount)
        }
        general_drink_button_remove.setOnClickListener {
            YoYo.with(Techniques.Landing)
                    .duration(300)
                    .repeat(0)
                    .playOn(general_drink_button_remove)
            if (drinkCount > 0) {
                drinkCount--
                setDrinkCount(drinkCount)
            }
        }
    }

    private fun setDrinkCount(drinkCount: Int) {
        val count = MessageFormat.format(resources.getString(R.string.general_water_count), drinkCount)
        general_drink_count_textView.text = count
        prefs.drinkCount = drinkCount
        callBackActivity.setWater(count)
    }

    override fun nextIngestion(nextIngestion: String) {
        general_next_ingestion.text = nextIngestion
    }

    override fun nowIngestion(nowIngestion: String) {
        general_now_ingestion.text = nowIngestion
    }

    override fun setWater(water: String) {
        general_drink_count_textView.text = water
    }
}
