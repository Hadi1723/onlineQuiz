package com.example.g1onlinequiz.gui

import android.content.ContentValues
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.g1onlinequiz.R
import com.example.g1onlinequiz.server.QuizServerBuilder
import retrofit2.Call
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class EndOfQuiz : AppCompatActivity() {

    private lateinit var userPerformance: com.example.g1onlinequiz.data.Result
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_of_quiz)


        findViewById<TextView>(R.id.finalScore).setText("${intent.getIntExtra("Score", 0)}%")


        findViewById<Button>(R.id.endButton).setOnClickListener{

            //userPerformance =  com.example.g1onlinequiz.data.Result(intent.getIntExtra("Score", 0), "Test 2")

            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            val formatted = current.format(formatter)

            val requestCall = QuizServerBuilder.builder.addUserScore(intent.getStringExtra("UserName2"), intent.getIntExtra("Score", 0), intent.getStringExtra("Quiz1"), formatted)


            requestCall.enqueue(object: retrofit2.Callback<com.example.g1onlinequiz.data.Result> {

                override fun onResponse(
                    call: Call<com.example.g1onlinequiz.data.Result>,
                    response: Response<com.example.g1onlinequiz.data.Result>
                ) {
                    if (response.isSuccessful) {
                        //val destinationList = response.body()!!
                        //destiny_recycler_view.adapter = DestinationAdapter(destinationList)

                        finish() // Move back to DestinationListActivity

                    }
                }

                override fun onFailure(call: Call<com.example.g1onlinequiz.data.Result>, t: Throwable) {
                    t.message?.let { Log.e(ContentValues.TAG, it) }
                }


            })

            //finish()
        }
    }
}