package com.example.googlesignin.models

data class QuestionsResponse(
    val success: Boolean? = null,
    val questionData: ArrayList<QuestionData>? = null
)

data class QuestionData(
    val question: String? = null,
    val optionData: ArrayList<OptionData>? = null
)

data class OptionData(
    val option: String? = null
)