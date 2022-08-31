package com.example.g1onlinequiz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.g1onlinequiz.R
import com.example.g1onlinequiz.data.Quiz

class QuizListAdapter(
    private val context: Context,
    private val detailModels: List<Quiz>,
    private val clickListener: (Quiz) -> Unit

) : RecyclerView.Adapter<QuizListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.quiz_selection, parent, false)
        return ViewHolder(view) {
            clickListener(detailModels!![it])
        }
    }

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

        fun bind(position: Int) {
            val name = itemView.findViewById<TextView>(R.id.idQuizName)
            val lengthQuiz = itemView.findViewById<TextView>(R.id.idQuizLength)

            var model: Quiz = detailModels!!.get(position)

            name.setText("Quiz Name: ${model.getName()}")
            lengthQuiz.setText("Length: ${model.getLengthQuiz().toString()} Questions")

        }

    }

}