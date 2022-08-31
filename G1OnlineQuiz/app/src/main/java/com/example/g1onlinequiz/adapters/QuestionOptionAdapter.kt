package com.example.g1onlinequiz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.g1onlinequiz.R
import com.example.g1onlinequiz.data.Question
import com.example.g1onlinequiz.data.Quiz

class QuestionOptionAdapter(
    private val context: Context,
    private val detailModels: List<String>,
    private val clickListener: (String) -> Unit

) : RecyclerView.Adapter<QuestionOptionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.question_item, parent, false)
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
            itemView.findViewById<TextView>(R.id.idQuestionItem).setText(detailModels!!.get(position))
            //itemView.findViewById<TextView>(R.id.idQuestionItem).setText("heyy")


        }

    }

}