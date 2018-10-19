package com.example.parsiphal.ingestion.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.parsiphal.ingestion.R
import com.example.parsiphal.ingestion.model.FiveIngModel
import kotlinx.android.synthetic.main.fragment_stats.view.*

//TODO Отображение данных из DB
//TODO Чтение из DB на основании типа питания

class StatsFragment : MvpAppCompatFragment() {

    private val items: ArrayList<FiveIngModel> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_stats, container, false)

        getFiveIngModelList()

        root.stats_recycler_view.layoutManager = LinearLayoutManager(context)
        root.stats_recycler_view.adapter = StatsFiveIngAdapter(items, context)
        return root
    }




    private fun getFiveIngModelList(): List<FiveIngModel> {
        items.add(FiveIngModel(1,
                "08/10/2018",
                "Вес: 100кг",
                "Завтрак в 09:00",
                "Перекус 1 в 10:20",
                "Обед в 13:00",
                "Перекус 2 в 17:00",
                "Ужин в 20:30",
                "Выпито воды: 7 ст."))
        items.add(FiveIngModel(1,
                "09/10/2018",
                "Вес: 90кг",
                "Завтрак в 09:00",
                "Перекус 1 в 10:20",
                "Обед в 13:00",
                "Перекус 2 в 17:00",
                "Ужин в 20:30",
                "Выпито воды: 7 ст."))
        items.add(FiveIngModel(1,
                "10/10/2018",
                "Вес: 90кг",
                "Завтрак в 09:00",
                "Перекус 1 в 10:20",
                "Обед в 13:00",
                "Перекус 2 в 17:00",
                "Ужин в 20:30",
                "Выпито воды: 7 ст."))
        items.add(FiveIngModel(1,
                "11/10/2018",
                "Вес: 90кг",
                "Завтрак в 09:00",
                "Перекус 1 в 10:20",
                "Обед в 13:00",
                "Перекус 2 в 17:00",
                "Ужин в 20:30",
                "Выпито воды: 7 ст."))
        items.add(FiveIngModel(1,
                "12/10/2018",
                "Вес: 90кг",
                "Завтрак в 09:00",
                "Перекус 1 в 10:20",
                "Обед в 13:00",
                "Перекус 2 в 17:00",
                "Ужин в 20:30",
                "Выпито воды: 7 ст."))
        items.add(FiveIngModel(1,
                "13/10/2018",
                "Вес: 90кг",
                "Завтрак в 09:00",
                "Перекус 1 в 10:20",
                "Обед в 13:00",
                "Перекус 2 в 17:00",
                "Ужин в 20:30",
                "Выпито воды: 7 ст."))
        items.add(FiveIngModel(1,
                "14/10/2018",
                "Вес: 90кг",
                "Завтрак в 09:00",
                "Перекус 1 в 10:20",
                "Обед в 13:00",
                "Перекус 2 в 17:00",
                "Ужин в 20:30",
                "Выпито воды: 7 ст."))
        items.add(FiveIngModel(1,
                "15/10/2018",
                "Вес: 90кг",
                "Завтрак в 09:00",
                "Перекус 1 в 10:20",
                "Обед в 13:00",
                "Перекус 2 в 17:00",
                "Ужин в 20:30",
                "Выпито воды: 7 ст."))
        items.add(FiveIngModel(1,
                "16/10/2018",
                "Вес: 90кг",
                "Завтрак в 09:00",
                "Перекус 1 в 10:20",
                "Обед в 13:00",
                "Перекус 2 в 17:00",
                "Ужин в 20:30",
                "Выпито воды: 7 ст."))
        items.add(FiveIngModel(1,
                "17/10/2018",
                "Вес: 90кг",
                "Завтрак в 09:00",
                "Перекус 1 в 10:20",
                "Обед в 13:00",
                "Перекус 2 в 17:00",
                "Ужин в 20:30",
                "Выпито воды: 7 ст."))
        items.add(FiveIngModel(1,
                "18/10/2018",
                "Вес: 90кг",
                "Завтрак в 09:00",
                "Перекус 1 в 10:20",
                "Обед в 13:00",
                "Перекус 2 в 17:00",
                "Ужин в 20:30",
                "Выпито воды: 7 ст."))
        items.add(FiveIngModel(1,
                "19/10/2018",
                "Вес: 90кг",
                "Завтрак в 09:00",
                "Перекус 1 в 10:20",
                "Обед в 13:00",
                "Перекус 2 в 17:00",
                "Ужин в 20:30",
                "Выпито воды: 7 ст."))
        return items
    }
}