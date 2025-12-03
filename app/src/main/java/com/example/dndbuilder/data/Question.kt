package com.example.dndbuilder.data


data class AnswerOption(
    val id: String,
    val text: String
)

data class Question(
    val id: String,
    val text: String,
    val options: List<AnswerOption>
)
