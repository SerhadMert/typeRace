package com.example.typerace.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.typerace.R
import com.example.typerace.dto.DataDTO

class ScoreListHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context)
        .inflate(R.layout.item_card, viewGroup, false)) {

    private val username by lazy { itemView.findViewById<TextView>(R.id.user_name) }
    private val score by lazy { itemView.findViewById<TextView>(R.id.score) }

    fun bindTo(dataDTO: DataDTO  ) {


        username.text = dataDTO.username
        score.text = dataDTO.score.toString()
}
}