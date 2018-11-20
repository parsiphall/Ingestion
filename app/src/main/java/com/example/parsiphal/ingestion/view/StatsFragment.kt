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

class StatsFragment : MvpAppCompatFragment() {

    private var items: List<FiveIngModel> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_stats, container, false)

        getFiveIngModelList()
        items.reversed()

        root.stats_recycler_view.layoutManager = LinearLayoutManager(context)
        root.stats_recycler_view.adapter = StatsFiveIngAdapter(items, context)
        return root
    }


    private fun getFiveIngModelList(): List<FiveIngModel> {
        items = (activity?.applicationContext as MainApp).getDataBase().getDao().getIngestions()
        return items
    }
}