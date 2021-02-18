package com.example.typerace

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_rank.*
import kotlinx.android.synthetic.main.activity_rank.view.*


class RankActivity : AppCompatActivity () {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rank)


        val username= findViewById<TextView>(R.id.txt_kullanici_adi)

        val db = FirebaseFirestore.getInstance()

        val docRef = db.collection("users").document("aTYqUC1wB3Nlhv2DpK8bigDQpbq2")
        docRef.get()
                .addOnSuccessListener { document ->
                    if(document!=null){
                        Log.d("exist","DocumentSnapshot data : ${document.data}")
                        username.text=document.getString("nickname")

                    } else {
                        Log.d("noexist","No such document")
                    }
                }
                .addOnFailureListener{exception ->
                    Log.d("errordb","get failed with",exception)
                }
    }
}