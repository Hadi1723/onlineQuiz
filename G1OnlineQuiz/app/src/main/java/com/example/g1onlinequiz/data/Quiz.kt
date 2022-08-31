package com.example.g1onlinequiz.data

data class Quiz(
    private val Name: String,
    private val Questions: List<Question>
) {
    fun getName(): String {
        return Name
    }

    fun getQuestions(): List<Question> {
        return Questions
    }

    fun getLengthQuiz(): Int {
        return Questions.size
    }
}