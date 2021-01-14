package com.example.typerace

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class FinishActivity : AppCompatActivity() {

    lateinit var scoreTxt: TextView
    lateinit var wordTxt: TextView
    lateinit var replayBt: Button
    lateinit var mainBt: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        supportActionBar?.hide()

        scoreTxt = findViewById(R.id.crd1_score)
        wordTxt = findViewById(R.id.crd1_true_false_word)
        replayBt = findViewById(R.id.replayBt)
        mainBt = findViewById(R.id.mainBt)


        val trueWord = Integer.parseInt(intent.getStringExtra(SCORE_MESSAGE_T))
        val falseWord = Integer.parseInt(intent.getStringExtra(SCORE_MESSAGE_F))

        scoreTxt.text = "SKOR  " + (trueWord * 5 + falseWord * 1)
        wordTxt.text = "1 dakikada " + (trueWord + falseWord) + " kelime yazdınız.\n bunlardan " + falseWord + " tanesi yanlıştı."

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


        //TİMERDAN VAZGEÇTİK AMA YİNE DE EKLEYEBİLİRİZ
        /*val timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // TODO
                //      TEXTVİEW KOY ONA EŞİTLE= (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                //BURAYA DOKUNMA SAAT BİTİMİ BURASI
                *//* val intent = Intent(this@FinishActivity, MainActivity::class.java)
                 startActivity(intent)
                 finish()*//*
            }
        }
        timer.start()*/

    }
}



