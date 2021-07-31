package com.example.alert_dialog_animation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_layout.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShowDialog.setOnClickListener {
            showAlertDialog(R.layout.dialog_layout)
        }

    }

    private fun showAlertDialog(layout: Int) {
        val view = LayoutInflater.from(this).inflate(layout, null, false)
        val dialogBuilder = AlertDialog.Builder(this).setView(view)
        val alertDialog = dialogBuilder.create()
        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        alertDialog.show()

        view.btnDissmiss.setOnClickListener {
            alertDialog.dismiss()
        }
    }
}
