package com.example.parsiphal.ingestion.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.parsiphal.ingestion.R
import com.example.parsiphal.ingestion.presenter.StartPresenter
import com.example.parsiphal.ingestion.presenter.interfaces.MainView
import com.example.parsiphal.ingestion.presenter.interfaces.StartView
import java.text.MessageFormat
import kotlinx.android.synthetic.main.fragment_start.start_button as startButton
import kotlinx.android.synthetic.main.fragment_start.welcome_textView as welcomeText
import kotlinx.android.synthetic.main.fragment_start.weight_textView as weightTextView
import kotlinx.android.synthetic.main.fragment_start.welcome_editText as welcomeEditText

//TODO Обработка кнопки "Начать". Запись в DB. Транслирование в MainActivity(Room LiveData?)
//TODO Проверка на новый день? Изменение проверки недели от нового дня.

class StartFragment : MvpAppCompatFragment(), StartView {

    @InjectPresenter
    lateinit var startPresenter: StartPresenter
    lateinit var callBackActivity: MainView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        callBackActivity = context as MainView
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun setWelcome(welcome: Int) {
        welcomeText.setText(when (welcome) {
            1 -> R.string.welcome_morning
            2 -> R.string.welcome_afternoon
            3 -> R.string.welcome_evening
            4 -> R.string.welcome_night
            else -> R.string.error
        })
    }

    override fun isNewDay(day: String, date: String) {
        if (prefs.lastUseDay != date) {
            prefs.lastUseDay = date
            if (day != resources.getString(R.string.welcome_monday)) {
                weightGone()
            } else {
                startButton.isEnabled = false
                welcomeEditText.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {}

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        startButton.isEnabled = true
                        prefs.thisWeekWeight = welcomeEditText.text.toString()
                    }
                })
            }
        } else {
            weightGone()
        }
        startButton.setOnClickListener {
            YoYo.with(Techniques.Landing)
                    .duration(100)
                    .repeat(1)
                    .playOn(startButton)
            callBackActivity.setWeight(MessageFormat.format(resources.getString(R.string.general_this_week_weight), prefs.thisWeekWeight))
            callBackActivity.fragmentPlace(GeneralFragment(), 1)
        }
    }

    private fun weightGone() {
        weightTextView.visibility = View.GONE
        welcomeEditText.visibility = View.GONE
    }
}
