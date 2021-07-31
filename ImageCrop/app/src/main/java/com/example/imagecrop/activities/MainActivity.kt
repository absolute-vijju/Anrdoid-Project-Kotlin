package com.example.imagecrop.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.imagecrop.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

    }

    private fun init() {
        btnImageActivity.setOnClickListener(this)
        btnVideoActivity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnImageActivity -> startActivity(Intent(this, ImageDashboardActivity::class.java))
            R.id.btnVideoActivity -> startActivity(Intent(this, VideoDashboardActivity::class.java))
        }
    }

}
