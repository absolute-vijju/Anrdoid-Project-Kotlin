package com.example.livedata_viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainActivityViewModel : ViewModel() {

    private var randomNumber: MutableLiveData<Int>? = null

    fun getRandomNumber(): MutableLiveData<Int> {
        if (randomNumber == null) {
            generateRandomNumber()
        }
        return randomNumber!!
    }

    private fun generateRandomNumber() {
        randomNumber!!.value = Random.nextInt()
    }

}