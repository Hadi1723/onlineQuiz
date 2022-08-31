package com.example.g1onlinequiz.gui

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.g1onlinequiz.R
import com.example.g1onlinequiz.Util
import com.example.g1onlinequiz.adapters.QuestionOptionAdapter
import com.example.g1onlinequiz.adapters.QuizListAdapter
import com.example.g1onlinequiz.checkingAnswer
import com.example.g1onlinequiz.data.Question
import com.example.g1onlinequiz.data.Quiz
import com.example.g1onlinequiz.server.QuizServerBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Response

class QuizQuestion : AppCompatActivity() {

    private lateinit var listOfQuestions: RecyclerView
    private var questions: List<Question> = listOf()

    private var numberCorrect = 0
    private var numberWrong = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        listOfQuestions = findViewById(R.id.questionOptions)

        findViewById<TextView>(R.id.numberCorrect).setText("Correct: $numberCorrect")
        findViewById<TextView>(R.id.numberWrong).setText("Incorrect: $numberWrong")

        getQuestions()

        //Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()
        //Toast.makeText(applicationContext, "${questions.size}", Toast.LENGTH_SHORT).show()

    }

    private fun getQuestions() {
        val requestCall = QuizServerBuilder.builder.getQuizQuestions(intent.getStringExtra("Quiz"))

        requestCall.enqueue(object: retrofit2.Callback<List<Question>> {

            override fun onResponse(
                call: Call<List<Question>>,
                response: Response<List<Question>>
            ) {
                if (response.isSuccessful) {
                    questions = response.body()!!

                    displayQuestion(questions.size - 1, 0)

                }
            }

            override fun onFailure(call: Call<List<Question>>, t: Throwable) {
                t.message?.let { Log.e(ContentValues.TAG, it) }
            }

        })

    }

    private fun displayQuestion(size: Int, performance: Int) {

        if (size < 0) {

            //var a = Firebase.auth

            finish()
            startActivity(Intent(this,EndOfQuiz::class.java).apply{
                putExtra("Quiz1",intent.getStringExtra("Quiz"))
                putExtra("Score", (performance * 100) / questions.size)
                putExtra("UserName2", intent.getStringExtra("UserName1"))
            })
        } else {
            findViewById<TextView>(R.id.questionName).setText(questions[size].getQuestionName())

            val questionOptions = listOf(questions[size].getOption1(), questions[size].getOption2(), questions[size].getOption3(), questions[size].getOption4())

            //Toast.makeText(applicationContext, "${questionOptions}", Toast.LENGTH_SHORT).show()

            listOfQuestions.adapter = QuestionOptionAdapter(applicationContext, questionOptions) {

                if (checkingAnswer.verifyAnswer(questions[size].getQuestionName(), it) == 1) {
                    numberCorrect += 1
                    findViewById<TextView>(R.id.numberCorrect).setText("Correct: $numberCorrect")
                } else {
                    numberWrong += 1
                    findViewById<TextView>(R.id.numberWrong).setText("Incorrect: $numberWrong")
                }

                displayQuestion(size-1, performance + checkingAnswer.verifyAnswer(questions[size].getQuestionName(), it))
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_quiz, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.endQuiz -> Util.warnChange(this, "Are you sure you want to Quit?", null) {
                finish()
            }

        }

        return super.onOptionsItemSelected(item)
    }

}