package com.bodiesbyrachel.vnfitnessapp.utils

/**
This class contains all the Constants of the app.
 */
class Constants {

    enum class WeightType {
        GAIN, LOSS
    }

    companion object {
        const val SELECTED_WORKOUT_DATA = "selected_workout_data"
        const val WORKOUT_TYPE = "workout_type"
        const val WORKOUT_PREVIEW_DATA = "workout_preview_data"
        const val WORKOUT_VIDEO_DATA = "workout_video_data"
        const val WEIGHT_TYPE = "weight_type"
        const val RECOMMENDED_WORKOUT = "recommended_workout"
        const val TRANSITION_NAME = "transition_name"
        const val SHOW_INTRO_VIDEO="show_intro_video"
        const val LOGIN_STATUS="login_status"
    }
}