package com.example.getdatafromserver

import okhttp3.Response

data class UserData(
    val success: Boolean? = null,
    val responseData: List<ResponseData>? = null
)

data class ResponseData(
    val firstName: String? = null,
    val lastName: String? = null
)