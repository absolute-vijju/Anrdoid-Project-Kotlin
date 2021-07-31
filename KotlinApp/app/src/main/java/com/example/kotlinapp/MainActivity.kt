package com.example.kotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var responceModel: ResponceModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rev.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonblob.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiInterface = retrofit.create(ApiInterface::class.java).getData()


            .enqueue(object : Callback<ResponceModel> {
                override fun onFailure(call: Call<ResponceModel>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(
                    call: Call<ResponceModel>,
                    response: Response<ResponceModel>
                ) {

                    responceModel = response.body()!!

                    if (responceModel != null) {

                        Log.d("mydata", responceModel.success.toString())
                        Log.d("mydata", responceModel.total_cal.toString())
                        Log.d("mydata", responceModel.used_cal.toString())
                        Log.d("mydata", responceModel.remaining_cal.toString())

                        Log.d("mydata", responceModel.diary_items?.size.toString())

                        /*Log.d("mydata", responceModel.remaining_cal.toString())
                        Log.d("mydata", responceModel.remaining_cal.toString())*/
                    }

                }

            })


    }

}
