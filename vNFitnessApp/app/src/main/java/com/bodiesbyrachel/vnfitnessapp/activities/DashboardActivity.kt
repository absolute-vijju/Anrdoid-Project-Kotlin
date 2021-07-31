package com.bodiesbyrachel.vnfitnessapp.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.fragments.*
import com.bodiesbyrachel.vnfitnessapp.utils.FragmentUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.coroutines.*

class DashboardActivity : CustomActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        addFragmentFromActivity(WorkoutFragment.newInstance(null))

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            Log.d("fragmentCount", supportFragmentManager.backStackEntryCount.toString())
            handleBackStack(supportFragmentManager.backStackEntryCount)
            when (item.itemId) {
                R.id.mWorkout -> FragmentUtils.replaceFragment(
                    supportFragmentManager,
                    R.id.flDashboardContainer,
                    WorkoutFragment.newInstance(null)
                )
                R.id.mNutrition -> FragmentUtils.replaceFragment(
                    supportFragmentManager,
                    R.id.flDashboardContainer,
                    NutritionFragment.newInstance(null)
                )
                R.id.mTips -> FragmentUtils.replaceFragment(
                    supportFragmentManager,
                    R.id.flDashboardContainer,
                    TipsFragment.newInstance(null)
                )
                R.id.mProgress -> FragmentUtils.replaceFragment(
                    supportFragmentManager,
                    R.id.flDashboardContainer,
                    ProgressFragment.newInstance(null)
                )
            }
            true
        }

    }

    private fun handleBackStack(count: Int) {
        if (count > 0)
            for (countIndex in 1..count) {
                supportFragmentManager.popBackStack()
            }
    }

    private fun addFragmentFromActivity(fragment: Fragment) {
        FragmentUtils.addFragment(
            supportFragmentManager,
            R.id.flDashboardContainer,
            fragment
        )
    }

    fun replaceFragmentFromActivity(fragment: Fragment) {
        FragmentUtils.replaceFragmentWithBackStack(
            supportFragmentManager,
            R.id.flDashboardContainer,
            fragment
        )
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStack()
        else {
            finish()
        }

    }
}
