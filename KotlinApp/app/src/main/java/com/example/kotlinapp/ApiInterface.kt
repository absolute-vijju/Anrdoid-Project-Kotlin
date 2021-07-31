package com.example.kotlinapp

import retrofit2.Call
import retrofit2.http.GET

public interface ApiInterface {
    @GET("761fd032-d845-11e9-9ec2-d533d2fa0122")
    fun getData(): Call<ResponceModel>
}
