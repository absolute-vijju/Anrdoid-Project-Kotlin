package com.thefit.models

data class NutritionResponse(
    val success: Boolean? = null,
    val nutritionData: List<NutritionData>? = null
)

data class NutritionData(
    val name: String? = null
)