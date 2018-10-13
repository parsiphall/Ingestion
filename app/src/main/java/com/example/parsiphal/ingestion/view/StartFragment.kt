package com.example.parsiphal.ingestion.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.parsiphal.ingestion.R
import com.example.parsiphal.ingestion.presenter.StartPresenter
import com.example.parsiphal.ingestion.presenter.interfaces.StartView
import kotlinx.android.synthetic.main.fragment_start.welcome_textView as welcomeText
import kotlinx.android.synthetic.main.fragment_start.weight_textView as weightTextView
import kotlinx.android.synthetic.main.fragment_start.welcome_editText as welcomeEditText

//TODO Обработка кнопки "Начать". Запись в DB. Транслирование в MainActivity(Room LiveData?)
//TODO Проверка на новый день? Изменение проверки недели от нового дня.

class StartFragment : MvpAppCompatFragment(), StartView {

    @InjectPresenter
    lateinit var startPresenter: StartPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun setWelcome(welcome: Int) {
        when (welcome) {
            1 -> welcomeText.setText(R.string.welcome_morning)
            2 -> welcomeText.setText(R.string.welcome_afternoon)
            3 -> welcomeText.setText(R.string.welcome_evening)
            4 -> welcomeText.setText(R.string.welcome_night)
        }
    }

    override fun isNewWeek(day: String) {
        if (!day.equals(R.string.welcome_monday)) {
            weightTextView.visibility = View.GONE
            welcomeEditText.visibility = View.GONE
        }
    }
}
