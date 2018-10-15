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
                "date",
                "weight",
                "breakfast",
                "snack1",
                "lunch",
                "snack2",
                "dinner",
                "water"))
        items.add(FiveIngModel(1,
                "date2",
                "weight",
                "breakfast",
                "snack1",
                "lunch",
                "snack2",
                "dinner",
                "water"))
        items.add(FiveIngModel(1,
                "date3",
                "weight",
                "breakfast",
                "snack1",
                "lunch",
                "snack2",
                "dinner",
                "water"))
        items.add(FiveIngModel(1,
                "date4",
                "weight",
                "breakfast",
                "snack1",
                "lunch",
                "snack2",
                "dinner",
                "water"))
        items.add(FiveIngModel(1,
                "date5",
                "weight",
                "breakfast",
                "snack1",
                "lunch",
                "snack2",
                "dinner",
                "water"))
        items.add(FiveIngModel(1,
                "date6",
                "weight",
                "breakfast",
                "snack1",
                "lunch",
                "snack2",
                "dinner",
                "water"))
        items.add(FiveIngModel(1,
                "date7",
                "weight",
                "breakfast",
                "snack1",
                "lunch",
                "snack2",
                "dinner",
                "water"))
        items.add(FiveIngModel(1,
                "date8",
                "weight",
                "breakfast",
                "snack1",
                "lunch",
                "snack2",
                "dinner",
                "water"))
        items.add(FiveIngModel(1,
                "date9",
                "weight",
                "breakfast",
                "snack1",
                "lunch",
                "snack2",
                "dinner",
                "water"))
        items.add(FiveIngModel(1,
                "date10",
                "weight",
                "breakfast",
                "snack1",
                "lunch",
                "snack2",
                "dinner",
                "water"))
        items.add(FiveIngModel(1,
                "date11",
                "weight",
                "breakfast",
                "snack1",
                "lunch",
                "snack2",
                "dinner",
                "water"))
        items.add(FiveIngModel(1,
                "date12",
                "weight",
                "breakfast",
                "snack1",
                "lunch",
                "snack2",
                "dinner",
                "water"))
        return items
    }
}