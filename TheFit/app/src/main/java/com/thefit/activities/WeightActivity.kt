package com.thefit.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.thefit.R
import com.thefit.fragments.WeightHistoryFragment
import com.thefit.utils.FragmentUtils

class WeightActivity : CustomActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)

        replaceFragmentFromActivity(WeightHistoryFragment.newInstance(null))
    }

    private fun replaceFragmentFromActivity(fragment: Fragment) {
        FragmentUtils.replaceFragment(supportFragmentManager, R.id.flWeightActivity, fragment)
    }

}