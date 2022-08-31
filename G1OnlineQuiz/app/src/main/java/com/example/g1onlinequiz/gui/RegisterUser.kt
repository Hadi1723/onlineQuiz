package com.example.g1onlinequiz.gui

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.g1onlinequiz.R
import com.example.g1onlinequiz.adapters.QuizListAdapter
import com.example.g1onlinequiz.data.Quiz
import com.example.g1onlinequiz.data.UserScoreDataItem
import com.example.g1onlinequiz.server.QuizServerBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Response

class RegisterUser : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    //private var util = Util()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        //binding = DataBindingUtil.setContentView(this,R.layout.activity_register_user )
        auth = Firebase.auth

        findViewById<Button?>(R.id.registerBtn).setOnClickListener{

            var newEmail = findViewById<EditText>(R.id.registerEmail).text.toString()
            var newPassword = findViewById<EditText>(R.id.registerPassword).text.toString()

            if (newEmail.isEmpty() || newPassword.isEmpty()) {
                Toast.makeText(this, "Fill in the information", Toast.LENGTH_SHORT).show()
            } else {
                //registerAccount(newEmail, newPassword)
                auth.createUserWithEmailAndPassword(newEmail, newPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            //startActivity(Intent(applicationContext, MainActivity::class.java))

                            // Sign in success, update UI with the signed-in user's information


                            var newUserResults: List<com.example.g1onlinequiz.data.Result> = mutableListOf()

                            val requestCall = QuizServerBuilder.builder.addNewUser(UserScoreDataItem(newEmail, newUserResults))


                            requestCall.enqueue(object: retrofit2.Callback<UserScoreDataItem> {

                                override fun onResponse(
                                    call: Call<UserScoreDataItem>,
                                    response: Response<UserScoreDataItem>
                                ) {
                                    if (response.isSuccessful) {
                                        val quizList = response.body()!!
                                        finish()
                                        startActivity(Intent(applicationContext, MainActivity::class.java).apply {
                                            //
                                        })
                                    }
                                }

                                override fun onFailure(call: Call<UserScoreDataItem>, t: Throwable) {
                                    t.message?.let { Log.e(ContentValues.TAG, it) }
                                }

                            })



                        } else {
                            Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        findViewById<Button?>(R.id.backToLoginBtn).setOnClickListener{
            finish()
        }

    }

    /*
    private fun registerAccount(newUserEmail: String, newUserPassword: String) {
        auth.createUserWithEmailAndPassword(newUserEmail, newUserPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(this, "Log in Failed", Toast.LENGTH_SHORT).show()
                }
            }
    } */

}