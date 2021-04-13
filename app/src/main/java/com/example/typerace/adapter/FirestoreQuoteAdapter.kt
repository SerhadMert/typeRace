package com.example.typerace.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.typerace.R
import com.example.typerace.activity.User
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.extensions.LayoutContainer


class FirestoreQuoteAdapter (options: FirestoreRecyclerOptions<User>) : FirestoreRecyclerAdapter<User, FirestoreQuoteAdapter.ViewHolder>(options) {

    lateinit var username :TextView
    lateinit var score : TextView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        username =view.findViewById<TextView>(R.id.user_name)
        score=view.findViewById<TextView>(R.id.score)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, item: User) {
        // val id = snapshots.getSnapshot(position).id


        holder.apply {
            username.text = item.username
            score.text = item.topScore.toString()
        }
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
}