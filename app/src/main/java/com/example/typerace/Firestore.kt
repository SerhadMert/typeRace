package com.example.typerace


import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Firestore {

    fun firstLoginFirebase(){
        val db = FirebaseFirestore.getInstance()
        val currentUser = FirebaseAuth.getInstance().currentUser
        val user = hashMapOf(
            "username" to (0..10).random().toString(),
        )
        if (currentUser != null) {
            db.collection("users").document(currentUser.uid)
                .set(user)
                .addOnSuccessListener { Log.d("pprofile", "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w("pprofile", "Error writing document", e) }
        }
    }
}