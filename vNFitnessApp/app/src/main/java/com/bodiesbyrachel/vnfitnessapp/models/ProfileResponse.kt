package com.bodiesbyrachel.vnfitnessapp.models

data class ProfileResponse(
    val success: Boolean? = null,
    val profileData: List<ProfileData>? = null
)

data class ProfileData(
    val title: String? = null,
    val desc: String? = null
)