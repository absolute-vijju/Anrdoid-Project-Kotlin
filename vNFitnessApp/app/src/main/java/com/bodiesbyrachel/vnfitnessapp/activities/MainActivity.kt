package com.bodiesbyrachel.vnfitnessapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.bodiesbyrachel.vnfitnessapp.R
import com.bodiesbyrachel.vnfitnessapp.utils.ConnectionDetector

class MainActivity : CustomActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val check = ConnectionDetector.isNetworkConnected(this)

//        Log.d("mydata", check.toString())

        if (!check)
            ConnectionDetector.showNoInternet(this)

    }
}
