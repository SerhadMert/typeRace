package com.example.typerace.services


import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.typerace.activity.ProfileActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class Firestore {


    private fun firstLoginFirebase() {
        val db = getDatabase()
        val currentFirebaseUser = getCurrentFirebaseUser()
        val user = hashMapOf(
            "username" to (0..10000).random().toString(),
            "topScore" to 0
        )
        db.collection("users").document(currentFirebaseUser.uid)
            .set(user)
            .addOnSuccessListener { Log.d("ffirebase", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w("ffirebase", "Error writing document", e) }
    }

    fun setScore(score: Long) {
        val db = getDatabase()
        val currentFirebaseUser = getCurrentFirebaseUser()

        db.collection("users").document(currentFirebaseUser.uid).update("topScore", score)
            .addOnSuccessListener {
                Log.d("ffirebase setScore", "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                Log.w("ffirebase setScore", "Error updating document", e)
            }


    }

    fun getScore(): Long {
        var score = 0L
        val db = getDatabase()
        val currentFirebaseUser = getCurrentFirebaseUser()

        db.collection("users").document(currentFirebaseUser.uid).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null) {
                    Log.d("ffirebase getScore", "DocumentSnapshot data : ${documentSnapshot.data}")
                    score = documentSnapshot.getLong("topScore")!!
                } else {
                    Log.d("ffirebase getScore", "No such document")
                }
            }.addOnFailureListener { exception ->
                Log.d("ffirebase", "get failed with", exception)
            }
        return score
    }

    fun updateUsername(username: String, context: Context) {
        val db = getDatabase()
        val currentFirebaseUser = getCurrentFirebaseUser()
        db.collection("users").document(currentFirebaseUser.uid).update("username", username)
            .addOnSuccessListener {
                Log.d("ffirebase", "DocumentSnapshot successfully updated!")

            }
            .addOnFailureListener { e ->
                Log.w("ffirebase", "Error updating document", e)
                Toast.makeText(context, "Değişim başarısız", Toast.LENGTH_SHORT).show()
            }
    }

    fun getUsername(context: Context, usernameShow: TextView) {
        val db = getDatabase()
        val currentFirebaseUser = getCurrentFirebaseUser()

        db.collection("users").document(currentFirebaseUser.uid).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot != null) {
                    Log.d("ffirebase", "DocumentSnapshot data : ${documentSnapshot.data}")
                    usernameShow.text = documentSnapshot.getString("username").toString()
                    usernameShow.visibility = View.VISIBLE

                } else {
                    Log.d("ffirebase", "No such document")
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                Log.d("ffirebase", "get failed with", exception)
            }
    }


    fun getRank(context: Context, listView: ListView) {
        val db = getDatabase()
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

    fun firebaseAuthWithGoogle(idToken: String, auth: FirebaseAuth) {
        val activity = ProfileActivity()
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("ffirebase", "signInWithCredential:success")

                    if (task.result!!.additionalUserInfo!!.isNewUser) {
                        Log.d("ffirebase", "onComplete: new user ")
                        this.firstLoginFirebase()
                    } else {
                        Log.d("ffirebase", "onComplete: old user ")
                    }

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("ffirebase", "signInWithCredential:failure", task.exception)
                    // ...
                }


            }
    }

    private fun getCurrentFirebaseUser(): FirebaseUser {
        var currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            //OLASI BİR GİRİ ŞHATASINDAN DOLAYI BURASI VAR KAÇIŞ RAMPASI GİBİ DÜŞÜN
            Thread.sleep(1000)
            Log.w("ffirebase", "800 delaye girdi")
            currentUser = FirebaseAuth.getInstance().currentUser
        } else {
            currentUser = FirebaseAuth.getInstance().currentUser
        }
        return currentUser!!
    }

    fun getDatabase(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}