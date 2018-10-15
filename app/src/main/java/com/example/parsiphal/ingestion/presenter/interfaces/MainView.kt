package com.example.parsiphal.ingestion.presenter.interfaces

import android.support.v4.app.Fragment

interface MainView: BaseView {

    fun setWeight(weight: String)
    fun setWater(water: String)
    fun fragmentPlace(fragment: Fragment, item: Int)
}