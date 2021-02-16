package com.example.typerace


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

    lateinit var exitBT: Button
    lateinit var idProf: TextView
    lateinit var givenName:TextView
    lateinit var email:TextView
    lateinit var fUser: FirebaseUser
    lateinit var image_profile: ImageView

    lateinit var personName: String
    lateinit var personGivenName: String
    lateinit var personFamilyName: String
    lateinit var personEmail: String
    lateinit var personId: String
    lateinit var personPhoto: Uri

    private lateinit var acct: GoogleSignInAccount
    private  lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

}