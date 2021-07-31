package com.bodiesbyrachel.vnfitnessapp.models

data class WeightResponse(
    val success: Boolean? = null,
    val mealSummary: List<MealSummary>? = null
)

data class MealSummary(
    val mealName: String? = null,
    val desc: String? = null
)