package com.bodiesbyrachel.vnfitnessapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.fragments.IntroVideoFragment
import com.bodiesbyrachel.vnfitnessapp.fragments.WelcomeFragment
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import com.bodiesbyrachel.vnfitnessapp.utils.FragmentUtils
import com.bodiesbyrachel.vnfitnessapp.utils.SharedPreference

class LoginActivity : CustomActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val showIntroVideo = SharedPreference.getBoolean(this, Constants.SHOW_INTRO_VIDEO, true)
        if (showIntroVideo)
            addFragmentFromActivity(IntroVideoFragment.newInstance(null))
        else
            addFragmentFromActivity(WelcomeFragment.newInstance(null))

    }

    fun addFragmentFromActivity(fragment: Fragment) {
        FragmentUtils.addFragment(
            supportFragmentManager,
            R.id.flLoginContainer,
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
