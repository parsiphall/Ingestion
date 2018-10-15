package com.example.parsiphal.ingestion.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.parsiphal.ingestion.R
import com.example.parsiphal.ingestion.model.FiveIngModel

class StatsFiveIngAdapter(private val items: List<FiveIngModel>,
                          private val context: Context?) : RecyclerView.Adapter<StatsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        return StatsViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.stats_tab_type_0, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        holder.date.text = items[position].date
        holder.weight.text = items[position].weight
        holder.breakfast.text = items[position].breakfast
        holder.snack1.text = items[position].snack1
        holder.lunch.text = items[position].lunch
        holder.snack2.text = items[position].snack2
        holder.dinner.text = items[position].dinner
        holder.water.text = items[position].water
    }
}