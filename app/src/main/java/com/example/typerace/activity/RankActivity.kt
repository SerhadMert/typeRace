package com.example.typerace.activity

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.typerace.R
import com.example.typerace.services.Firestore
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
        val currentUser=findViewById<TextView>(R.id.txt_currentUser)
        val fireStore = Firestore()
        fireStore.getUsername(applicationContext, currentUser)
        fireStore.getRank(applicationContext,rank)

    }
}



