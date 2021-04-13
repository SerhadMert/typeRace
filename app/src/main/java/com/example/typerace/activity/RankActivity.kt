package com.example.typerace.activity

import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.typerace.R
import com.example.typerace.adapter.FirestoreQuoteAdapter
import com.example.typerace.adapter.ScoreViewHolder
import com.example.typerace.dto.DataDTO
import com.example.typerace.services.Firestore
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.*
import io.grpc.internal.JsonUtil.getString
import kotlinx.android.synthetic.main.activity_rank.*
import kotlinx.android.synthetic.main.item_card.*
import com.firebase.ui.firestore.FirestoreRecyclerAdapter as FirestoreRecyclerAdapter


class RankActivity : AppCompatActivity () {

    private lateinit var recyclerView: RecyclerView
    private var firestoreListener: ListenerRegistration? = null
    private var notesList = mutableListOf<User>()
    private var db : FirebaseFirestore? = null
    private var adapter: FirestoreRecyclerAdapter<User, ScoreViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.apply { this.layoutManager = LinearLayoutManager(this@RankActivity) }


        val firestore =  Firestore()
        db = firestore.getDatabase()


        getScoreList()

        firestoreListener = db!!.collection("users")
            .addSnapshotListener(EventListener { documentSnapshots, e ->
                if (e != null) {
                    Log.e("rankk", "Listen failed!", e)
                    return@EventListener
                }

                notesList = mutableListOf()

                if (documentSnapshots != null) {
                    for (doc in documentSnapshots) {
                        val note = doc.toObject(User::class.java)
                        note.username?.let { Log.d("rankk", it) }
                        note.id = doc.id
                        notesList.add(note)
                    }
                }

                adapter!!.notifyDataSetChanged()
                recyclerView.adapter = adapter
            })


    }

    private fun getScoreList() {

        val query = db!!.collection("users")

        val response = FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User::class.java)
                .build()


        adapter = object : FirestoreRecyclerAdapter<User, ScoreViewHolder>(response) {
            override fun onBindViewHolder(holder: ScoreViewHolder, position: Int, model: User) {
                val user = notesList[position]

                holder.username.text = user.username
                holder.score.text = user.topScore.toString()

            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_card, parent, false)

                return ScoreViewHolder(view)
            }
        }

        adapter!!.notifyDataSetChanged()
        recyclerView.adapter = adapter
    }
}









