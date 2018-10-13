package com.example.parsiphal.ingestion.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.parsiphal.ingestion.R
import com.example.parsiphal.ingestion.presenter.interfaces.StartView
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : MvpAppCompatFragment(), StartView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun setWelcome(welcome: String) {
        welcome_textView.text = welcome
    }
}
