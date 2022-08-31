package com.example.g1onlinequiz.data

data class Result(
    var Score: Int?,
    var quizName: String?,
    var lastAttempt: String?
) {
    @JvmName("getQuizName1")
    fun getQuizName(): String? {
        return quizName
    }

    @JvmName("getScore1")
    fun getScore(): Int? {
        return Score
    }

    @JvmName("getLastAttempt1")
    fun getLastAttempt(): String? {
        return lastAttempt
    }
}