package com.example.typerace.activity

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.typerace.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_rank.*


class RankActivity : AppCompatActivity () {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)


        val rank = findViewById<ListView>(R.id.list_view)
        val username=ArrayList<String>()
        val score= ArrayList<Int>()

        val db = FirebaseFirestore.getInstance()




        db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d("getProp", "${document.id} => ${document.data}")

                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("getProp", "Error getting documents: ", exception)
                }


    }
}