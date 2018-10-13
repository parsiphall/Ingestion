package com.example.parsiphal.ingestion.view

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.parsiphal.ingestion.R
import com.example.parsiphal.ingestion.presenter.interfaces.MainView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

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

        fragmentPlace(StartFragment())
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
            R.id.nav_new_day -> fragmentPlace(StartFragment())
            R.id.nav_general -> fragmentPlace(GeneralFragment())
            R.id.nav_stats -> fragmentPlace(StatsFragment())
            R.id.nav_options -> fragmentPlace(OptionsFragment())
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun fragmentPlace(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.content_main, fragment)
                .commit()
    }

    override fun setWeight(weight: String) {
        nav_this_week_weight.text = weight
    }

    override fun setWater(water: String) {
        nav_water_count.text = water
    }
}
