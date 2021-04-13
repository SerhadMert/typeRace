package com.example.typerace.activity

import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.typerace.R
import com.example.typerace.adapter.ScoreListAdapter
import com.example.typerace.dto.DataDTO
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import io.grpc.internal.JsonUtil.getString
import kotlinx.android.synthetic.main.activity_rank.*
import kotlinx.android.synthetic.main.item_card.*


class RankActivity : AppCompatActivity () {

    private lateinit var recyclerView: RecyclerView
    val posts = ArrayList<DataDTO>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.apply { this.layoutManager = LinearLayoutManager(this@RankActivity) }
        getScoreList()



    }

    private fun getScoreList() {
        val db = getDatabase()
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("getProp", "${document.id} => ${document.data}")
                    val properties = document.get("${document.data}",DataDTO::class.java)
                    if(properties!=null){
                        posts.add(properties)
                    }
                    val adapter=ScoreListAdapter(posts)
                    recyclerView.adapter=adapter
                    adapter.notifyDataSetChanged()
                }
            }
            .addOnFailureListener { exception ->
                Log.d("getProp", "Error getting documents: ", exception)
            }

    }

    private fun getDatabase(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
     }









