package com.example.typerace.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.typerace.R

class UserViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    var username: TextView
    var score: TextView

    init {
        username = view.findViewById(R.id.user_name)
        score = view.findViewById(R.id.score)

    }
}