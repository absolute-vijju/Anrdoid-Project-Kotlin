package com.example.googlesignin.room

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "question_answer", primaryKeys = ["question"])
data class QuestionEntity(
    @ColumnInfo(name = "question")
    var question: String,
    @ColumnInfo(name = "answer")
    var answer: String
)