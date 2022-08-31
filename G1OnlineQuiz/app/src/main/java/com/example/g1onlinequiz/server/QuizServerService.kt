package com.example.g1onlinequiz.server

import com.example.g1onlinequiz.data.Question
import com.example.g1onlinequiz.data.Quiz
import com.example.g1onlinequiz.data.Result
import com.example.g1onlinequiz.data.UserScoreDataItem
import retrofit2.Call
import retrofit2.http.*

interface QuizServerService {
    // incorporates path parameters
    // Note: you can apply query strings and path parameters together
    @GET("quiz/")
    fun getQuizzes(): Call<List<Quiz>>

    @GET("quiz/{name}")
    fun getQuizQuestions(@Path("name") name: String?): Call<List<Question>>

    @GET("users/{name}")
    fun getUserScores(@Path("name") name: String?): Call<List<Result>>

    // posting into the server
    // although we get back the new data, we don't have to use it
    //@Headers("Content-Type: application/json")
    @FormUrlEncoded
    @PUT("users/{name}")
    fun addUserScore(@Path("name") name: String?, @Field ("Score") Score: Int?, @Field("quizName") quizName: String?, @Field("lastAttempt") lastAttempt: String): Call<Result>


    @POST("users/")
    fun addNewUser(@Body userResults: UserScoreDataItem): Call<UserScoreDataItem>

    @DELETE("users/{name}")
    fun deleteUser(@Path("name") name: String?): Call<Unit>
}