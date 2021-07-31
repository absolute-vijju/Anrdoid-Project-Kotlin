package com.thefit.models

data class TrainingResponse(
    val success: Boolean? = null,
    val trainingData: List<TrainingData>? = null
)

data class TrainingData(
    val name: String? = null
)