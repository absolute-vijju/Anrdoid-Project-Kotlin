package com.example.livedataandviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.livedataandviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.startTimer()

        mainActivityViewModel.second.observe(this, Observer {
            mBinding.myVariable = it.toString()
        })

        mainActivityViewModel.finished.observe(this, Observer {
            if (it)
                Toast.makeText(applicationContext, "Timer Finished", Toast.LENGTH_SHORT).show()
        })

        mBinding.btnStartTimer.setOnClickListener {
            mainActivityViewModel.startTimer()
        }

        mBinding.btnStopTimer.setOnClickListener {
            mainActivityViewModel.stopTimer()
        }


    }
}