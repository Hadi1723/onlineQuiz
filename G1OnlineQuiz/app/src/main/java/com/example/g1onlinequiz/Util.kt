package com.example.g1onlinequiz

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog

class Util {

    companion object{
        fun warnChange(context: Context, title: String, view: View?, positiveClickListener: View.OnClickListener) {
            AlertDialog.Builder(context)
                .setTitle(title)
                .setView(view)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok") {_, _ ->
                    positiveClickListener.onClick(null)
                }.show()
        }
    }
}