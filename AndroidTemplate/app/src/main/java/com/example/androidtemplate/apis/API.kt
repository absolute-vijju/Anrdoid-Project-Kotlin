package com.example.androidtemplate.apis

import retrofit2.Response
import retrofit2.http.GET

interface API {
    @GET("3657b768-2afe-11eb-ac03-fd133409a351")
    suspend fun getData(): Response<List<String>>
}