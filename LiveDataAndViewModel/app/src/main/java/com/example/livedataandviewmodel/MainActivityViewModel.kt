package com.example.livedataandviewmodel

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private lateinit var timer: CountDownTimer
    var second = MutableLiveData<Int>()
    var finished = MutableLiveData<Boolean>()

    fun startTimer() {
        timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                second.value = (p0 / 1000).toInt()
                Log.d("Tag", second.value.toString())
            }

            override fun onFinish() {
                finished.value = true
                second.value = 0
            }

        }.start()
    }

    fun stopTimer() {
        timer.cancel()
    }

}