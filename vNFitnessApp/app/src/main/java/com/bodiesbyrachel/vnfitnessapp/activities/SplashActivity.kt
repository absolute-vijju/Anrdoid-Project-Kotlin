package com.bodiesbyrachel.vnfitnessapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.utils.Constants
import com.bodiesbyrachel.vnfitnessapp.utils.FragmentUtils
import com.bodiesbyrachel.vnfitnessapp.utils.GeneralFunction
import com.bodiesbyrachel.vnfitnessapp.utils.SharedPreference
import kotlinx.coroutines.*

class SplashActivity : CustomActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        GlobalScope.launch(Dispatchers.Main){
            delay(1000L)
            if (SharedPreference.getBoolean(this@SplashActivity, Constants.LOGIN_STATUS, false))
                startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
            else
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }


    override fun onBackPressed() {
        //super.onBackPressed()
    }
}
