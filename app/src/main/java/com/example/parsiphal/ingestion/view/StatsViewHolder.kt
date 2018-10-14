package com.example.parsiphal.ingestion.view

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.stats_tab_type_0.view.*

class StatsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val date = view.stats_date_text_view!!
    val weight = view.stats_weight_text_view!!
    val breakfast = view.stats_breakfast_text_view!!
    val snack1 = view.stats_snack1_text_view!!
    val lunch = view.stats_lunch_text_view!!
    val snack2 = view.stats_snack2_text_view!!
    val dinner = view.stats_dinner_text_view!!
    val water = view.stats_water_text_view!!
}