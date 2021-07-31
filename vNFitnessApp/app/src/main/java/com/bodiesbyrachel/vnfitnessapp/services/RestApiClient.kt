package com.bodiesbyrachel.vnfitnessapp.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestApiClient {

    companion object {
        fun getApiClient(): API {
            return Retrofit.Builder().baseUrl("https://jsonblob.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(API::class.java)
        }
    }

}