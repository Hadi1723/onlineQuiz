package com.example.g1onlinequiz.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QuizServerBuilder {

    // we need this constant which will have the protocol and the domain of the URL
    const val URL = "http://10.0.2.2:4001/"

    // creating the OkHTTP Client
    // remember, Retrofit is built ontop of okHTTP
    //private val okHttp = OkHttpClient.Builder()

    // creating the retrofit builder
    val builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(URL)
        .build()
        .create(QuizServerService::class.java)
}