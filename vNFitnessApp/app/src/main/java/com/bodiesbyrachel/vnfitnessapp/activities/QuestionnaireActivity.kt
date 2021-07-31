package com.bodiesbyrachel.vnfitnessapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.fragments.StepOneFragment
import com.bodiesbyrachel.vnfitnessapp.utils.FragmentUtils

class QuestionnaireActivity : CustomActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questionnaire)

        FragmentUtils.addFragment(
            supportFragmentManager,
            R.id.flQuestionnaireContainer,
            StepOneFragment.newInstance(null)
        )
    }

    fun replaceFragmentFromActivity(fragment: Fragment) {
        FragmentUtils.replaceFragmentWithBackStack(
            supportFragmentManager,
            R.id.flQuestionnaireContainer,
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
