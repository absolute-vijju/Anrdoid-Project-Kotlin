package com.example.googlesignin.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT * FROM question_answer")
    fun getQuestionAnswer(): List<QuestionEntity>

    @Insert
    fun insertQuestionAnswer(questionEntity: QuestionEntity)

    @Delete
    fun deleteQuestionAnswer(questionEntity: QuestionEntity)
}