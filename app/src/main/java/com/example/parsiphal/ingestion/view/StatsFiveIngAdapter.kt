package com.example.parsiphal.ingestion.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.parsiphal.ingestion.R
import com.example.parsiphal.ingestion.model.FiveIngModel
import java.text.MessageFormat

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
        holder.weight.text = MessageFormat.format(context!!.resources.getString(R.string.stats_week_weight), items[position].weight)
        holder.breakfast.text = MessageFormat.format(context.resources.getString(R.string.stats_breakfast), items[position].breakfast)
        holder.snack1.text = MessageFormat.format(context.resources.getString(R.string.stats_snack1), items[position].snack1)
        holder.lunch.text = MessageFormat.format(context.resources.getString(R.string.stats_lunch), items[position].lunch)
        holder.snack2.text = MessageFormat.format(context.resources.getString(R.string.stats_snack2), items[position].snack2)
        holder.dinner.text = MessageFormat.format(context.resources.getString(R.string.stats_dinner), items[position].dinner)
        holder.water.text = MessageFormat.format(context.resources.getString(R.string.stats_water), items[position].water)
    }
}