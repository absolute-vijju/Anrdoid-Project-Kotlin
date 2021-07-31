package com.thefit.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.thefit.R
import com.thefit.fragments.InfoFragment
import com.thefit.fragments.NutritionFragment
import com.thefit.fragments.TrainingFragment
import com.thefit.utils.FragmentUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : CustomActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragmentFromActivity(TrainingFragment.newInstance(null))

        btmNav.setOnNavigationItemSelectedListener {
            handleBackStack(supportFragmentManager.backStackEntryCount)
            when (it.itemId) {
                R.id.miTraining -> replaceFragmentFromActivity(TrainingFragment.newInstance(null))
                R.id.miNutrition -> replaceFragmentFromActivity(NutritionFragment.newInstance(null))
                R.id.miInfo -> replaceFragmentFromActivity(InfoFragment.newInstance(null))

            }
            true
        }

    }

    private fun replaceFragmentFromActivity(fragment: Fragment) {
        FragmentUtils.replaceFragment(supportFragmentManager, R.id.flMainActivity, fragment)
    }

    private fun handleBackStack(count: Int) {
        if (count > 0)
            for (countIndex in 1..count) {
                supportFragmentManager.popBackStack()
            }
    }

}