package com.example.androidtemplate.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import com.example.androidtemplate.R

class ConnectivityReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p1 != null) {
            if (ConnectivityManager.CONNECTIVITY_ACTION == p1.action) {

                val newtWorkStatus =
                    p1.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
                if (p0 != null) {
                    if (newtWorkStatus)
                        Toast.makeText(p0, p0.getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(p0, p0.getString(R.string.internet_connected), Toast.LENGTH_SHORT).show()
                }

            }


        }
    }
}