package com.example.typerace.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.typerace.*
import kotlinx.android.synthetic.main.activity_finish.*


class FinishActivity : AppCompatActivity() {

    private lateinit var scoreTxt: TextView
    private lateinit var wordTxt: TextView
    private lateinit var replayBt: Button
    private lateinit var mainBt: Button


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)



        supportActionBar?.hide()

        scoreTxt = findViewById(R.id.crd1_score)
        wordTxt = findViewById(R.id.crd1_true_false_word)
        replayBt = findViewById(R.id.replayBt)
        this.mainBt = findViewById(R.id.mainBt)


        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val trueWord = Integer.parseInt(intent.getStringExtra(SCORE_MESSAGE_T))
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val falseWord = Integer.parseInt(intent.getStringExtra(SCORE_MESSAGE_F))

        scoreTxt.text = "SKOR  ${(trueWord * 5)+(falseWord * 1)}"
        wordTxt.text = if(falseWord==0 && trueWord!=0)
            "1 dakikada $trueWord kelime yazdınız.\n Bunların hepsi doğru :)"
         else if(falseWord==0 && trueWord==0)
             "Dostum biraz uğraşsaydın keşke :("
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
}



