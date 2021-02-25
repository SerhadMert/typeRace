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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class ProfileActivity : AppCompatActivity() {

    private lateinit var exitBT: Button
    private lateinit var signInBt: SignInButton
    private lateinit var idProf: TextView
    private lateinit var givenName:TextView
    private lateinit var email:TextView
    private lateinit var imageProfile: ImageView
    private lateinit var profLayout:RelativeLayout
    private lateinit var prof2Layout: LinearLayout
    private lateinit var btHome : ImageButton
    private lateinit var usernameEdit : EditText
    private lateinit var usernameShow : TextView
    private lateinit var editProfile : Button
    private lateinit var saveProfile : Button

    private lateinit var username : String
    private lateinit var personName: String
    private lateinit var personGivenName: String
    private lateinit var personEmail: String
    private lateinit var personPhoto: Uri

    private lateinit var acct: GoogleSignInAccount
    private  lateinit var mGoogleSignInClient: GoogleSignInClient
    private val rcSignIn: Int = 1
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        signInBt = findViewById(R.id.sign_in_button)
        idProf = findViewById(R.id.user_profile_name)
        imageProfile = findViewById(R.id.user_profile_photo)
        givenName = findViewById(R.id.user_given_name)
        exitBT = findViewById(R.id.exit_bt)
        email = findViewById(R.id.email)
        profLayout = findViewById(R.id.profile_layout)
        prof2Layout = findViewById(R.id.profile2_layout)
        btHome = findViewById(R.id.home_bt)
        usernameEdit = findViewById(R.id.username_edit)
        usernameShow = findViewById(R.id.username_show)
        editProfile = findViewById(R.id.edit_profile)
        saveProfile = findViewById(R.id.save_profile)

        getUsername()


        val acct2 = GoogleSignIn.getLastSignedInAccount(baseContext)
        if (acct2 != null) {
            acct=acct2
            displayProfile()


        }
        createRequest()


        auth = Firebase.auth

        signInBt.setOnClickListener { signIn() }

        editProfile.setOnClickListener {
            usernameShow.visibility = View.GONE
            usernameEdit.visibility = View.VISIBLE
            saveProfile.visibility = View.VISIBLE
            editProfile.visibility = View.GONE
            usernameEdit.setText(usernameShow.text)
        }
        saveProfile.setOnClickListener((View.OnClickListener {
            val username2 = usernameEdit.text.toString()
            usernameShow.visibility = View.VISIBLE
            usernameEdit.visibility = View.GONE
            saveProfile.visibility = View.GONE
            editProfile.visibility = View.VISIBLE

            saveNickname(username2)
        }))


        exitBT.setOnClickListener {
            mGoogleSignInClient.signOut()
            startActivity(Intent(this@ProfileActivity, ProfileActivity::class.java))
            finish()
        }

        btHome.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
            finish()
        }


    }

    private fun getCurrentFirebaseUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

    private fun getDatabase(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    private fun saveNickname(username2 :String){
        if(username2 != username){
            val db = getDatabase()
            val currentFirebaseUser = getCurrentFirebaseUser()
            if (currentFirebaseUser !=null){
                db.collection("users").document(currentFirebaseUser.uid).update("nickname", username2)
                        .addOnSuccessListener { Log.d("username update", "DocumentSnapshot successfully updated!")
                            getUsername()}
                        .addOnFailureListener { e -> Log.w("username update", "Error updating document", e) }
            }


        }
    }

    private fun getUsername (){


        val db = getDatabase()

        val currentFirebaseUser = getCurrentFirebaseUser()

        if(currentFirebaseUser !=null){
            val docRef = db.collection("users").document(currentFirebaseUser.uid)
            docRef.get()
                    .addOnSuccessListener { document ->
                        if(document!=null){
                            Log.d("getUsername", "DocumentSnapshot data : ${document.data}")
                            username= document.getString("nickname").toString()
                            usernameShow.text = username

                        } else {
                            Log.d("getUsername", "No such document")
                        }
                    }
                    .addOnFailureListener{ exception ->
                        Log.d("getUsername", "get failed with", exception)
                    }
        }
    }

    private fun displayProfile(){
        if(GoogleSignIn.getLastSignedInAccount(this)!=null){
            acct = GoogleSignIn.getLastSignedInAccount(applicationContext)!!
            personName = acct.displayName!!
            personGivenName = acct.givenName!!
            personEmail = acct.email!!
            personPhoto = acct.photoUrl!!

            signInBt.visibility = View.INVISIBLE
            profLayout.visibility= View.VISIBLE
            prof2Layout.visibility= View.VISIBLE


            idProf.text = personName
            givenName.text = personGivenName
            email.text = personEmail
            Glide.with(this).load(personPhoto).into(imageProfile)


        }
    }

    private fun createRequest(){
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun signIn(){
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, rcSignIn)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from GOOGLE

        if (requestCode == rcSignIn) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                handleSignInResult()
                firebaseAuthWithGoogle(account?.idToken!!)
            } catch (e: ApiException) {

                Toast.makeText(applicationContext, "Google hesabına giriş yapılırken bir hata oluştu" + e.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun handleSignInResult() {
        try {
            //val account = completedTask.getResult(ApiException::class.java)

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

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("firebase auth", "signInWithCredential:success")
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("firebase auth", "signInWithCredential:failure", task.exception)
                    // ...
                }

                // ...
            }
    }








}