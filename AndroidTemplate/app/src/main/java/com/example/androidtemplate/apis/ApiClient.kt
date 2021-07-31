package com.example.androidtemplate.apis

import com.example.androidtemplate.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {
        private var retrofit: Retrofit? = null

        fun getApiClient(): API {
            if (retrofit != null)
                return retrofit!!.create(API::class.java)
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(15000, TimeUnit.MILLISECONDS)
                .build()
            retrofit =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .client(okHttpClient)
                    .build()
            return retrofit!!.create(API::class.java)
        }
    }
}