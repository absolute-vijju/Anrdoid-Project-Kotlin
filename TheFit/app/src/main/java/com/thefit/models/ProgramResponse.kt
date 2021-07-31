package com.thefit.models

data class ProgramResponse(
    val success: Boolean? = null,
    val weekData: List<WeekData>? = null
)

data class WeekData(
    val week: Int? = null,
    val gymData: GymData? = null,
    val homeData: HomeData? = null,
    val exerciseData: List<ExerciseData>? = null
)

data class GymData(
    val daysData: List<DaysData>? = null,
    val exerciseData: List<ExerciseData>? = null
)

data class HomeData(
    val daysData: List<DaysData>? = null,
    val exerciseData: List<ExerciseData>? = null
)

data class DaysData(
    val day: Int? = null,
    val exerciseData: List<ExerciseData>? = null
)

data class ExerciseData(
    val exerciseName: String? = null,
    val desc: String? = null,
    val increaseDifficulty: String? = null,
    val decreaseDifficulty: String? = null,
    val previousPersonalBest: String? = null,
    val alternateExercise: String? = null,
    var selected: Boolean? = null
)