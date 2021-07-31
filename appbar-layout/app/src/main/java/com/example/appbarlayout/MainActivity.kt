package com.example.appbarlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = getString(R.string.toolbar_title)
        toolbar.subtitle = getString(R.string.toolbar_subtitle)
        toolbar.setNavigationIcon(android.R.drawable.btn_star)
    }
}
