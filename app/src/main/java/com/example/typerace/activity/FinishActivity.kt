package com.example.typerace.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.typerace.*
import com.example.typerace.services.Firestore
import com.google.firebase.auth.FirebaseAuth


class FinishActivity : AppCompatActivity() {

    private lateinit var scoreTxt: TextView
    private lateinit var wordTxt: TextView
    private lateinit var replayBt: ImageButton
    private lateinit var mainBt: ImageButton
    private lateinit var usernameShow : TextView


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        supportActionBar?.hide()

        scoreTxt = findViewById(R.id.txt_score)
        wordTxt = findViewById(R.id.crd1_true_false_word)
        replayBt = findViewById(R.id.replay_bt)
        mainBt = findViewById(R.id.main_bt)
        usernameShow = findViewById(R.id.username_show)



        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val trueWord = Integer.parseInt(intent.getStringExtra(SCORE_MESSAGE_T))
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val falseWord = Integer.parseInt(intent.getStringExtra(SCORE_MESSAGE_F))

        val score = (trueWord*5) + (falseWord*1)

        val firestore = Firestore()
        if(FirebaseAuth.getInstance().currentUser != null){
            getUsername()
            usernameShow.visibility=View.VISIBLE
            if(firestore.getScore() < score){
                firestore.setScore(score.toLong())
            }

        }


        scoreTxt.text = "$score"
        wordTxt.text = if(falseWord==0 && trueWord!=0)
            "1 dakikada $trueWord kelime yazdınız.\n Bunların hepsi doğru :)"
         else if(falseWord==0 && trueWord==0)
             "Dostum biraz çabalasaydın keşke :("
             else
            "1 dakikada  ${trueWord + falseWord}  kelime yazdınız.\n Bunlardan $trueWord tanesi doğru  $falseWord  tanesi yanlış."

        replayBt.setOnClickListener {
            val intent = Intent(this@FinishActivity, GameActivity::class.java)
            startActivity(intent)
            finish()
        }
        mainBt.setOnClickListener {
            val intent = Intent(this@FinishActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }



    }
    private fun getUsername (){
        val fireStore = Firestore()
        fireStore.getUsername(applicationContext, usernameShow)
    }
}



