package com.example.typerace.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.typerace.dto.DataDTO
import java.util.*



class ScoreListAdapter(private val rankList : List<DataDTO>) : RecyclerView.Adapter<ScoreListHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreListHolder {
        return ScoreListHolder(parent)
    }
    override fun getItemCount(): Int {
        return rankList.size
    }

    override fun onBindViewHolder(holder: ScoreListHolder, position: Int) {
        holder.bindTo(rankList[position])


    }

}