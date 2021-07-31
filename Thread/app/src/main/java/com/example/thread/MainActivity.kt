package com.example.thread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler()

        btnStartTask.setOnClickListener {
            handler.post(runnable)
        }

        btnStopTask.setOnClickListener {
            handler.removeCallbacks(runnable)
        }

    }

    private val runnable = object : Runnable {
        override fun run() {
            handler.postDelayed(this, 1000)
            Log.d(TAG, "I am Running in Background")
        }
    }

}
