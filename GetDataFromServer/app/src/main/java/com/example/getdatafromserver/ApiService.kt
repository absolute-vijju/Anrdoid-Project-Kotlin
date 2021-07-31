package com.example.getdatafromserver

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("79b07ea5-2250-11eb-bc64-abef894e0040")
    suspend fun getUserData():Response<UserData>
}