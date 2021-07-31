package com.bodiesbyrachel.vnfitnessapp.models

data class WorkoutResponse(
    val success: Boolean? = null,
    val workoutData: List<WorkoutData>? = null
)

data class WorkoutData(
    val workoutName: String? = null,
    val daysData: List<DaysData>? = null
)

data class DaysData(
    val day: String? = null,
    val exerciseData: List<ExerciseData>? = null
)

data class ExerciseData(
    val exerciseName: String? = null,
    val data: List<Data>? = null
)

data class Data(
    val exercise: String? = null,
    val directions: String? = null,
    val round: String? = null,
    val reps: String? = null,
    val duration: String? = null
)