package com.example.handler_and_runnable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.content.ContextCompat
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


            if (btnStartTask.text.toString().equals(getString(R.string.start_task), true)) {
                btnStartTask.text = getString(R.string.stop_task)
                tvHint.setText(getString(R.string.task_running))
                handler.post(runnable)
                ll.setBackgroundColor(ContextCompat.getColor(this, R.color.running_task))
            } else {
                btnStartTask.text = getString(R.string.start_task)
                tvHint.setText(getString(R.string.task_stopped))
                handler.removeCallbacks(runnable)
                ll.setBackgroundColor(ContextCompat.getColor(this, R.color.stop_task))
            }
        }
    }

    private val runnable = object : Runnable {
        override fun run() {
            handler.postDelayed(this, 1000)
            Log.d(TAG, "I am Running in Background")
        }
    }
}
