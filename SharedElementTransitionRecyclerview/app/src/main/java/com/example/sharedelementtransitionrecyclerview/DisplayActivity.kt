package com.example.sharedelementtransitionrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_display.*

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)


        val selectedCustomer=Gson().fromJson(intent.getStringExtra("CustomerData"),Customer::class.java)
        val type=object :TypeToken<Array<String>>(){}.type
        val transitionArray=Gson().fromJson<Array<String>>(intent.getStringExtra("TransitionArray"),type)

        tvFirstName.transitionName=transitionArray[0]
        tvLastName.transitionName=transitionArray[1]
        tvGender.transitionName=transitionArray[2]

        tvFirstName.text=selectedCustomer.firstName
        tvLastName.text=selectedCustomer.lastName
        tvGender.text=selectedCustomer.gender


    }
}