package com.thefit.activities

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.thefit.R
import com.thefit.fragments.ProgramFragment
import com.thefit.fragments.SelectedWorkoutFragment
import com.thefit.models.ProgramResponse
import com.thefit.utils.FragmentUtils
import kotlinx.android.synthetic.main.fragment_selected_workout.*

class ProgramActivity : CustomActivity() {

    var programResponse: ProgramResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program)

        replaceFragmentFromActivity(ProgramFragment.newInstance(null))
    }

    private fun replaceFragmentFromActivity(fragment: Fragment) {
        FragmentUtils.replaceFragment(supportFragmentManager, R.id.flProgram, fragment)
    }

    fun replaceFragmentWithBackStack(fragment: Fragment) {
        FragmentUtils.replaceFragmentWithBackStack(supportFragmentManager, R.id.flProgram, fragment)
    }

    private fun checkOrientation() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onBackPressed() {
        checkOrientation()
        if (supportFragmentManager.findFragmentById(R.id.flProgram) is SelectedWorkoutFragment)
            (supportFragmentManager.findFragmentById(R.id.flProgram) as SelectedWorkoutFragment).releasePlayer()
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStack()
        else
            finish()
    }

}