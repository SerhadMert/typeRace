package com.example.typerace


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfileActivity : AppCompatActivity() {

    lateinit var exitBT: Button
    lateinit var signInBt: SignInButton
    lateinit var idProf: TextView
    lateinit var givenName:TextView
    lateinit var email:TextView
    lateinit var fUser: FirebaseUser
    lateinit var image_profile: ImageView
    lateinit var profLayout:RelativeLayout
    lateinit var prof2Layout: LinearLayout

    lateinit var personName: String
    lateinit var personGivenName: String
    lateinit var personFamilyName: String
    lateinit var personEmail: String
    lateinit var personId: String
    lateinit var personPhoto: Uri

    private lateinit var acct: GoogleSignInAccount
    private  lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions
    val RC_SIGN_IN: Int = 1
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        signInBt = findViewById(R.id.sign_in_button)
        idProf = findViewById(R.id.user_profile_name)
        image_profile = findViewById(R.id.user_profile_photo)
        givenName = findViewById(R.id.user_given_name)
        exitBT = findViewById(R.id.exitBt)
        email = findViewById(R.id.email)
        profLayout = findViewById(R.id.profile_layout)
        prof2Layout = findViewById(R.id.profile2_layout)

        var acct2 = GoogleSignIn.getLastSignedInAccount(getBaseContext())
        if (acct2 != null) {

            displayProfile()

        }
        createRequest();


        auth = Firebase.auth

        signInBt.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                signIn()
            }

        })


        exitBT.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mGoogleSignInClient.signOut()
                startActivity(Intent(this@ProfileActivity, ProfileActivity::class.java))
                finish()
            }

        })


    }
    fun createRequest(){
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }
    fun signIn(){
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)

        }
    }

    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>){
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            startActivity(Intent(this@ProfileActivity, ProfileActivity::class.java))
            finish()
            //updateUI(account);
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.statusCode)
            //updateUI(null);
        }
    }


    fun displayProfile(){
        if(GoogleSignIn.getLastSignedInAccount(this)!=null){
            acct = GoogleSignIn.getLastSignedInAccount(applicationContext)!!
            if (acct != null) {
                personName = acct.getDisplayName()!!
                personGivenName = acct.getGivenName()!!
                personEmail = acct.getEmail()!!
                personPhoto = acct.getPhotoUrl()!!

                signInBt.visibility = View.INVISIBLE
                profLayout.visibility= View.VISIBLE
                prof2Layout.visibility= View.VISIBLE


                idProf.setText(personName)
                givenName.setText(personGivenName)
                email.setText(personEmail)
                Glide.with(this).load(personPhoto).into(image_profile)


            }

        }
    }




}