package com.example.pubnubdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pubnub.api.PNConfiguration

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pnConfiguration = PNConfiguration()



    }
}
