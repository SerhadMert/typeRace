package com.example.typerace.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.typerace.R
import com.example.typerace.adapter.UserViewHolder
import com.example.typerace.services.Firestore
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_rank.*
import kotlinx.android.synthetic.main.item_card.*
import com.firebase.ui.firestore.FirestoreRecyclerAdapter as FirestoreRecyclerAdapter


class RankActivity : AppCompatActivity () {

    private lateinit var recyclerView: RecyclerView
    private var firestoreListener: ListenerRegistration? = null
    private var userlist = mutableListOf<User>()
    private var db : FirebaseFirestore? = null
    private var adapter: FirestoreRecyclerAdapter<User, UserViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.apply { this.layoutManager = LinearLayoutManager(this@RankActivity) }


        val firestore =  Firestore()
        db = firestore.getDatabase()


        getScoreList()

        this.firestoreListener = db!!.collection("users")
            .addSnapshotListener(EventListener { documentSnapshots, e ->
                if (e != null) {
                    Log.e("rankk", "Listen failed!", e)
                    return@EventListener
                }

                userlist = mutableListOf()

                if (documentSnapshots != null) {
                    for (doc in documentSnapshots) {
                        val user = doc.toObject(User::class.java)
                        user.id = doc.id
                        userlist.add(user)
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


        adapter = object : FirestoreRecyclerAdapter<User, UserViewHolder>(response) {
            override fun onBindViewHolder(holder: UserViewHolder, position: Int, model: User) {
                val user = userlist[position]

                holder.username.text = user.username
                holder.score.text = user.topScore.toString()

            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_card, parent, false)

                return UserViewHolder(view)
            }
        }

        adapter!!.notifyDataSetChanged()
        recyclerView.adapter = adapter
    }

    public override fun onStart() {
        super.onStart()

        adapter!!.startListening()
    }

    public override fun onStop() {
        super.onStop()

        adapter!!.stopListening()
    }
}









