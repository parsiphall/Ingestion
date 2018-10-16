package com.example.parsiphal.ingestion.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.parsiphal.ingestion.R
import kotlinx.android.synthetic.main.fragment_options.*

//TODO Выбор 3-х или 5-ти разового питания, сохранение в SharedPreferences

class OptionsFragment : MvpAppCompatFragment(), AdapterView.OnItemSelectedListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinnerSet()
    }

    private fun spinnerSet() {
        options_startHour_spinner.onItemSelectedListener = this
        val arrayAdapter = ArrayAdapter.createFromResource(activity!!, R.array.new_day_start_list, android.R.layout.simple_spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        options_startHour_spinner.adapter = arrayAdapter
        options_startHour_spinner.setSelection(prefs.startNewDayHour!!)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        prefs.startNewDayHour = position
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}