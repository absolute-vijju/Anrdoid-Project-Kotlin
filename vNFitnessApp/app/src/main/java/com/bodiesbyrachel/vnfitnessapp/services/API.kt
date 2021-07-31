package com.bodiesbyrachel.vnfitnessapp.services

import com.bodiesbyrachel.vnfitnessapp.models.ApiResponse
import com.bodiesbyrachel.vnfitnessapp.models.WorkoutResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface API {

    @GET("a5a48694-9b60-11ea-9753-6564b35b7e84")
    suspend fun getWorkoutData(): Response<WorkoutResponse>

}