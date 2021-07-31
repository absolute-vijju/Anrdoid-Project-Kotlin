package com.example.recyclerviewmotionlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var rvData: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        rvData = findViewById(R.id.rvData)

        rvData?.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = OptionAdapter(this, getData(), -1)
        }

    }

    private fun getData(): ArrayList<String> {
        val data = arrayListOf<String>()
        data.add("Option 1")
        data.add("Option 2")
        data.add("Option 3")
        data.add("Option 4")
        data.add("Option 5")
        return data
    }
}