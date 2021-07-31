package com.example.googlesignin.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreference {
    companion object {
        private fun getPreference(context: Context): SharedPreferences? {
            return context.getSharedPreferences("google_sign_in_pref", Context.MODE_PRIVATE)
        }

        fun save(context: Context, key: String, value: String) {
            getPreference(context)!!.edit().putString(key, value).apply()
        }

        fun getString(context: Context, key: String, defValue: String): String {
            return getPreference(context)!!.getString(key, defValue)!!
        }

        fun remove(context: Context, key: String) {
            getPreference(context)!!.edit().remove(key).apply()
        }

        fun removeAll(context: Context) {
            getPreference(context)!!.edit().clear().apply()
        }
    }
}