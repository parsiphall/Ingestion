package com.example.parsiphal.ingestion.presenter.interfaces

interface GeneralView : BaseView {

    fun nextIngestion(nextIngestion: String)
    fun nowIngestion(nowIngestion: String)
    fun setWater(water: String)
}