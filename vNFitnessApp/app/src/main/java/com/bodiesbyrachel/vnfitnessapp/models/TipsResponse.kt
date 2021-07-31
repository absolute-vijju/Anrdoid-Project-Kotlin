package com.bodiesbyrachel.vnfitnessapp.models

data class TipsResponse(
    val success: Boolean? = null,
    val tipsData: List<TipsData>? = null
)

data class TipsData(
    val tipTitle: String? = null,
    val imageUrl: String? = null,
    val tipDesc: String? = null
)