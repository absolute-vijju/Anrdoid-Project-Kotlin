package com.bodiesbyrachel.vnfitnessapp.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class SharedPreference(activity: Activity) {
    companion object {

        private fun getPreference(activity: Activity): SharedPreferences? {
            return activity.getSharedPreferences("mypref_file", Context.MODE_PRIVATE)
        }

        fun save(activity: Activity, key: String, value: Boolean) {
            getPreference(activity)!!.edit().putBoolean(key, value).apply()
        }

        fun getBoolean(activity: Activity, key: String, defValue: Boolean): Boolean {
            return getPreference(activity)!!.getBoolean(key, defValue)
        }

        fun remove(activity: Activity, key: String) {
            getPreference(activity)!!.edit().remove(key).apply()
        }

        fun removeAll(activity: Activity) {
            getPreference(activity)!!.edit().clear().apply()
        }
    }
}