package com.example.typerace

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class RankActivity : AppCompatActivity () {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)


        val username= findViewById<TextView>(R.id.username_edit)

        val db = FirebaseFirestore.getInstance()

        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser

        if(currentFirebaseUser !=null){
            val docRef = db.collection("users").document(currentFirebaseUser.uid)
            docRef.get()
                    .addOnSuccessListener { document ->
                        if(document!=null){
                            Log.d("getUsername", "DocumentSnapshot data : ${document.data}")
                            username.text=document.getString("nickname")

                        } else {
                            Log.d("getUsername", "No such document")
                        }
                    }
                    .addOnFailureListener{ exception ->
                        Log.d("getUsername", "get failed with", exception)
                    }
        }

    }
}