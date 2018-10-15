package com.example.parsiphal.ingestion.view

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.parsiphal.ingestion.R
import com.example.parsiphal.ingestion.presenter.interfaces.MainView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.nav_this_week_weight as thisWeekWeight
import kotlinx.android.synthetic.main.nav_header_main.nav_water_count as waterCount
import java.text.MessageFormat

//TODO Вода в initGUI


class MainActivity : MvpAppCompatActivity(), MainView, NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        initGUI()
    }

    private fun initGUI() {
        val startWeight = MessageFormat.format(resources.getString(R.string.general_this_week_weight), prefs.thisWeekWeight)
        val thisWeekWeightOnStart = nav_view.getHeaderView(0).findViewById<TextView>(R.id.nav_this_week_weight)
        thisWeekWeightOnStart.text = startWeight
        val startWater = MessageFormat.format(resources.getString(R.string.general_water_count), prefs.drinkCount)
        val waterCountOnStart = nav_view.getHeaderView(0).findViewById<TextView>(R.id.nav_water_count)
        waterCountOnStart.text = startWater
        fragmentPlace(StartFragment(), 0)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_new_day -> fragmentPlace(StartFragment(), 0)
            R.id.nav_general -> fragmentPlace(GeneralFragment(), 1)
            R.id.nav_stats -> fragmentPlace(StatsFragment(), 2)
            R.id.nav_options -> fragmentPlace(OptionsFragment(), 3)
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun fragmentPlace(fragment: Fragment, item: Int) {
        supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.content_main, fragment)
                .commit()
        nav_view.menu.getItem(item).isChecked = true
    }

    override fun setWeight(weight: String) {
        thisWeekWeight.text = weight
    }

    override fun setWater(water: String) {
        waterCount.text = water
    }
}
