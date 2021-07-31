package com.example.kotlinapp

class ResponceModel(
    val success: Boolean = false,
    val total_cal: Int = 0,
    val used_cal: Int = 0,
    val remaining_cal: Int = 0,
    val diary_items: List<Diary_items>? = null
) {
    inner class Diary_items(
        val id: Int = 0,
        val name: String = "",
        val quantity: Int = 0
    )

}