package com.bodiesbyrachel.vnfitnessapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.fragments.SelectedWorkoutFragment
import com.bodiesbyrachel.vnfitnessapp.fragments.WorkoutTypeFragment
import com.bodiesbyrachel.vnfitnessapp.fragments.WorkoutVideoFragment
import com.bodiesbyrachel.vnfitnessapp.models.WorkoutData
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import com.bodiesbyrachel.vnfitnessapp.utils.FragmentUtils
import com.google.gson.Gson

class SelectedWorkoutActivity : CustomActivity() {

    var selectedWorkoutData: WorkoutData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_workout)

        selectedWorkoutData = Gson().fromJson<WorkoutData>(
            intent.getStringExtra(Constants.SELECTED_WORKOUT_DATA),
            WorkoutData::class.java
        )

        val workoutType = intent.getStringExtra(Constants.WORKOUT_TYPE)
        val bundle = Bundle()
        if (workoutType.equals(getString(R.string.home), false))
            bundle.putString(Constants.WORKOUT_TYPE, getString(R.string.home))
        else
            bundle.putString(Constants.WORKOUT_TYPE, getString(R.string.gym))

        FragmentUtils.addFragment(
            supportFragmentManager,
            R.id.flSelectedWorkoutContainer,
            SelectedWorkoutFragment.newInstance(bundle)
        )
    }

    fun replaceFragmentFromActivity(fragment: Fragment) {
        FragmentUtils.replaceFragmentWithBackStack(
            supportFragmentManager,
            R.id.flSelectedWorkoutContainer,
            fragment
        )
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.flSelectedWorkoutContainer)
        if (fragment is WorkoutVideoFragment) {
            fragment.mainTimer?.cancel()
        }
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStack()
        else {
            finish()
        }
    }
}
