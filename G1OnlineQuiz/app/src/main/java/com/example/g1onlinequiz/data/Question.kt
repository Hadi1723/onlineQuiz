package com.example.g1onlinequiz.data

data class Question(
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String
) {
    fun getQuestionName(): String {
        return question
    }

    @JvmName("getOption11")
    fun getOption1(): String {
        return option1
    }

    @JvmName("getOption21")
    fun getOption2(): String {
        return option2
    }

    @JvmName("getOption31")
    fun getOption3(): String {
        return option3
    }

    @JvmName("getOption41")
    fun getOption4(): String {
        return option4
    }

}