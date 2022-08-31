package com.example.g1onlinequiz.gui

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.g1onlinequiz.R
import com.example.g1onlinequiz.adapters.QuizListAdapter
import com.example.g1onlinequiz.adapters.UserScoreAdapter
import com.example.g1onlinequiz.data.Quiz
import com.example.g1onlinequiz.server.QuizServerBuilder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Response

class UserScores : AppCompatActivity() {

    private lateinit var userItems: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_scores)

        findViewById<TextView>(R.id.userName).setText("List Of ${intent.getStringExtra("UserName")} Scores:")

        userItems = findViewById(R.id.listOfScores)


        val requestCall = QuizServerBuilder.builder.getUserScores(intent.getStringExtra("UserName"))

        /*
        val filter = HashMap<String, String>()
        filter["country"] = "India"
        filter["count"] = "1"

        val requestCall = ServiceBuilder.builder.getDestinationList(filter) */


        requestCall.enqueue(object: retrofit2.Callback<List<com.example.g1onlinequiz.data.Result>> {

            override fun onResponse(
                call: Call<List<com.example.g1onlinequiz.data.Result>>,
                response: Response<List<com.example.g1onlinequiz.data.Result>>
            ) {
                if (response.isSuccessful) {
                    val performance = response.body()!!
                    userItems.adapter = UserScoreAdapter(applicationContext, performance) {
                        Toast.makeText(applicationContext, "Heyy", Toast.LENGTH_SHORT).show()
                    }

                }
            }

            override fun onFailure(call: Call<List<com.example.g1onlinequiz.data.Result>>, t: Throwable) {
                t.message?.let { Log.e(ContentValues.TAG, it) }
            }


        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_score_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.homePage -> finish()

            R.id.logOut -> startActivity(Intent(this, SignIn::class.java))
        }

        return super.onOptionsItemSelected(item)
    }
}