package com.bodiesbyrachel.vnfitnessapp.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.provider.Settings


class ConnectionDetector {
    companion object {
        fun isNetworkConnected(activity: Activity): Boolean {
            val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
        }
        fun showNoInternet(activity: Activity){
            activity.startActivity(Intent(Settings.ACTION_DATA_ROAMING_SETTINGS))
        }
    }
}