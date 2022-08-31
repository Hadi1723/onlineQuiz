package com.example.g1onlinequiz.gui

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.g1onlinequiz.R
import com.example.g1onlinequiz.Util
import com.example.g1onlinequiz.adapters.QuizListAdapter
import com.example.g1onlinequiz.data.Quiz
import com.example.g1onlinequiz.server.QuizServerBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var quizSelections: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quizSelections = findViewById(R.id.listOfQuizzes)

        auth = Firebase.auth

        val requestCall = QuizServerBuilder.builder.getQuizzes()

        requestCall.enqueue(object: retrofit2.Callback<List<Quiz>> {

            override fun onResponse(
                call: Call<List<Quiz>>,
                response: Response<List<Quiz>>
            ) {
                if (response.isSuccessful) {
                    val quizList = response.body()!!
                    quizSelections.adapter = QuizListAdapter(applicationContext, quizList) {
                        //Toast.makeText(applicationContext, "Heyy", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, QuizQuestion::class.java).apply {
                            putExtra("Quiz", it.getName())
                            putExtra("UserName1", auth.currentUser?.email)
                        })

                    }

                }
            }

            override fun onFailure(call: Call<List<Quiz>>, t: Throwable) {
                t.message?.let { Log.e(ContentValues.TAG, it) }
            }


        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.allScores -> {
                startActivity(Intent(this, UserScores::class.java).apply {
                    putExtra("UserName", auth.currentUser?.email)
                })
            }

            R.id.signOut -> {
                finish()
                startActivity(Intent(this, SignIn::class.java))
            }

            R.id.deleteAccount -> {

                Util.warnChange(this, "Are you sure you want to delete account?", null) {
                    val requestCall = QuizServerBuilder.builder.deleteUser(auth.currentUser?.email)

                    requestCall.enqueue(object : retrofit2.Callback<Unit> {

                        override fun onResponse(
                            call: Call<Unit>,
                            response: Response<Unit>
                        ) {
                            if (response.isSuccessful) {
                                //val quizList = response.body()!!

                                val user = auth.currentUser!!

                                user.delete()
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            finish()
                                            startActivity(
                                                Intent(
                                                    applicationContext,
                                                    SignIn::class.java
                                                )
                                            )
                                        }
                                    }

                            }
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            t.message?.let { Log.e(ContentValues.TAG, it) }
                        }

                    })
                }


            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser

        if (currentUser == null) {
            startActivity(Intent(this, SignIn::class.java))
        }
    }
}