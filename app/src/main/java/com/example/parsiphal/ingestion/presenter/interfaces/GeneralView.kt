package com.example.parsiphal.ingestion.presenter.interfaces

interface GeneralView : BaseView {

    fun nextIngestion(nowHour: Int, nowMinute: Int)
    fun nowIngestion(feedNumber: Int)
    fun setWater(water: String)
    fun notification(notifyTime: Long)
}