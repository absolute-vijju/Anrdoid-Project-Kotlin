package com.bodiesbyrachel.vnfitnessapp.models

data class ProgressResponse(
    val success: Boolean? = null,
    val progressData: List<ProgressData>? = null
)

data class ProgressData(
    val title: String? = null,
    val before: String? = null,
    val current: String? = null
)