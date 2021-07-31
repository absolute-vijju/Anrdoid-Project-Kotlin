package com.example.androidtemplate.activities

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.androidtemplate.R
import com.example.androidtemplate.apis.ApiClient
import com.example.androidtemplate.receiver.ConnectivityReceiver
import com.example.androidtemplate.utils.ConnectionDetector
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val connectivityReceiver = ConnectivityReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerConnectivityReceiver()

        Toast.makeText(
            this,
            ConnectionDetector.isNetworkAvailable(this).toString(),
            Toast.LENGTH_SHORT
        ).show()

        /*val tvHello = findViewById<TextView>(R.id.tvHello)
        val animation = AnimationUtils.loadAnimation(this, R.anim.animation)
        animation.setInterpolator(this, android.R.interpolator.bounce)
        tvHello.animation = animation*/

        /*GlobalScope.launch {
            val response = ApiClient.getApiClient().getData()
            if (response.isSuccessful) {
                val data = response.body()
                for (i in data!!)
                    Log.d("mydata", i)
            } else
                Log.d("mydata", response.message())
        }*/
    }

    private fun registerConnectivityReceiver() {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(connectivityReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(connectivityReceiver)
    }
}