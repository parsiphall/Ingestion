package com.example.parsiphal.ingestion.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.parsiphal.ingestion.R
import com.example.parsiphal.ingestion.presenter.interfaces.GeneralView
import kotlinx.android.synthetic.main.fragment_general.*

class GeneralFragment : MvpAppCompatFragment(), GeneralView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_general, container, false)
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
