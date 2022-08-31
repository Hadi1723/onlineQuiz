package com.example.g1onlinequiz.gui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.g1onlinequiz.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignIn : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    //private var util = Util()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = Firebase.auth

        /*util.userAuthentication(R.id.loginBtn,
            R.id.logInEmail,
            R.id.logInPassword,
            true,
            R.id.signUpBtn) */

        findViewById<Button?>(R.id.loginBtn).setOnClickListener{

            var currentEmail = findViewById<EditText>(R.id.logInEmail).text.toString()
            var currentPassword = findViewById<EditText>(R.id.logInPassword).text.toString()

            if (currentEmail.isEmpty() || currentPassword.isEmpty()) {
                Toast.makeText(this, "Fill in the information", Toast.LENGTH_SHORT).show()
            } else {
                //signIn(currentEmail, currentPassword)
                auth.signInWithEmailAndPassword(currentEmail, currentPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))
                        } else {
                            Toast.makeText(this, "Log in Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        findViewById<Button?>(R.id.signUpBtn).setOnClickListener{
            startActivity(Intent(this, RegisterUser::class.java))
        }

    }

}