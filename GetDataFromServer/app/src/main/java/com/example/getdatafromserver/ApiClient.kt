package com.example.getdatafromserver

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private const val BASE_URL = "https://jsonblob.com/api/"
        private var retrofit: Retrofit? = null
        fun getApiClient(): ApiService {
            return if (retrofit != null)
                retrofit!!.create(ApiService::class.java)
            else {
                retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofit!!.create(ApiService::class.java)
            }
        }
    }
}