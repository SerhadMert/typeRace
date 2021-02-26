package com.example.typerace.services


import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.typerace.activity.ProfileActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class Firestore {

    private fun firstLoginFirebase(){
        val db = getDatabase()
        val currentFirebaseUser = getCurrentFirebaseUser()
        val user = hashMapOf(
            "username" to (0..10).random().toString(),
        )
        db.collection("users").document(currentFirebaseUser.uid)
            .set(user)
            .addOnSuccessListener { Log.d("pprofile", "DocumentSnapshot successfully written!") }
            .addOnFailureListener { e -> Log.w("pprofile", "Error writing document", e) }
    }
    fun updateUsername(username :String,context :Context){
        val db = getDatabase()
        val currentFirebaseUser = getCurrentFirebaseUser()
        db.collection("users").document(currentFirebaseUser.uid).update("username", username)
                .addOnSuccessListener {
                    Log.d("username update", "DocumentSnapshot successfully updated!")

                }
                .addOnFailureListener { e ->
                    Log.w("username update", "Error updating document", e)
                    Toast.makeText(context,"Değişim başarısız", Toast.LENGTH_SHORT).show()
                }
    }
    fun getUsername(context: Context,usernameShow : TextView):String{
        var username ="null"
        val db = getDatabase()
        val currentFirebaseUser = getCurrentFirebaseUser()

        db.collection("users").document(currentFirebaseUser.uid).get()
                .addOnSuccessListener { documentSnapshot ->
                    if(documentSnapshot!=null){
                        Log.d("pprofile", "DocumentSnapshot data : ${documentSnapshot.data}")
                        username= documentSnapshot.getString("username").toString()
                        usernameShow.text=documentSnapshot.getString("username").toString()

                    } else {
                        Log.d("pprofile", "No such document")
                    }
                }.addOnFailureListener{ exception ->
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                    Log.d("pprofile", "get failed with", exception)
                }
        return username
    }
    fun firebaseAuthWithGoogle(idToken: String,auth: FirebaseAuth) {
        val activity = ProfileActivity()
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("pprofile", "signInWithCredential:success")

                        if(task.result!!.additionalUserInfo!!.isNewUser){
                            Log.d("pprofile", "onComplete: new user ")
                            val firestore = Firestore()
                            firestore.firstLoginFirebase()
                        }else{
                            Log.d("pprofile", "onComplete: old user ")
                        }

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("pprofile", "signInWithCredential:failure", task.exception)
                        // ...
                    }


                }
    }

    private fun getCurrentFirebaseUser(): FirebaseUser {
        var currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null){
            //OLASI BİR GİRİ ŞHATASINDAN DOLAYI BURASI VAR KAÇIŞ RAMPASI GİBİ DÜŞÜN
            Thread.sleep(1000)
            Log.w("pprofile", "800 delaye girdi")
            currentUser = FirebaseAuth.getInstance().currentUser
        }else{
            currentUser = FirebaseAuth.getInstance().currentUser
        }
        return currentUser!!
    }

    private fun getDatabase(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}