package com.thefit.models

data class WeightHistoryResponse(
    val success: Boolean? = null,
    val weightHistoryData: List<WeightHistoryData>? = null
)

data class WeightHistoryData(
    val date: String? = null,
    val weight: String? = null
)