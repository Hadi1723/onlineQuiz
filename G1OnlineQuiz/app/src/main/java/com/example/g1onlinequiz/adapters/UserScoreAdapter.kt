package com.example.g1onlinequiz.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.g1onlinequiz.R
import com.example.g1onlinequiz.data.Quiz
import com.example.g1onlinequiz.data.Result
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class UserScoreAdapter(
    private val context: Context,
    private val detailModels: List<Result>,
    private val clickListener: (Result) -> Unit

) : RecyclerView.Adapter<UserScoreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.user_score, parent, false)
        return ViewHolder(view) {
            clickListener(detailModels!![it])
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return detailModels!!.size
    }

    // and we're going to define our own view holder which will encapsulate the memory card view
    inner class ViewHolder(itemView: View, clickAtPosition: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener{
                clickAtPosition(adapterPosition)
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(position: Int) {
            val name = itemView.findViewById<TextView>(R.id.idQuiz)
            val score = itemView.findViewById<TextView>(R.id.idQuizScore)
            val lastAttemptDate = itemView.findViewById<TextView>(R.id.idLastAttempt)

            var model: Result = detailModels!!.get(position)

            name.setText(model.getQuizName())
            score.setText("Recent Score: ${model.getScore().toString()}%")

            lastAttemptDate.setText("Last Attempt at: ${model.getLastAttempt()}")
        }

    }

}