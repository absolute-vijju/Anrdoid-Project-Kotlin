package com.example.livedata_viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mainActivityViewModel =
            ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        val randomNumber = mainActivityViewModel.getRandomNumber()
        randomNumber.observe(this, object : Observer<Int?> {
            override fun onChanged(t: Int?) {
                tvGeneratedNumber.text = t.toString()
            }
        })



        btGenerateNumber.setOnClickListener(View.OnClickListener {

        })


    }
}
