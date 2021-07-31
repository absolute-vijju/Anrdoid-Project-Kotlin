package com.example.getdatafromserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            val response=ApiClient.getApiClient().getUserData()

            if (response.isSuccessful)
                Log.d("mydata",Gson().toJson(response.body()))
            else
                Log.d("mydata",response.message())

        }


    }
}