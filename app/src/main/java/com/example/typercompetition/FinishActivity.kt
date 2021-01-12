package com.example.typercompetition

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val textView = findViewById<TextView>(R.id.crd1_text1)
        val textView2 = findViewById<TextView>(R.id.crd1_text2)
        val trueWord = Integer.parseInt(intent.getStringExtra(SCORE_MESSAGE_T))
        val falseWord = Integer.parseInt(intent.getStringExtra(SCORE_MESSAGE_F))

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)

        textView.text = "D=" + trueWord + "Y=" + falseWord
        textView2.text="SKOR = " + (trueWord - falseWord)

        button1.setOnClickListener {
            val intent = Intent(this@FinishActivity, GameActivity::class.java)
            startActivity(intent)
            finish()
        }
        button2.setOnClickListener {
            val intent = Intent(this@FinishActivity, MainActivity::class.java )
            startActivity(intent)
            finish()
        }

        val timer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // TODO
                //      TEXTVİEW KOY ONA EŞİTLE= (millisUntilFinished/1000).toString()
            }

            override fun onFinish() {
                //BURAYA DOKUNMA SAAT BİTİMİ BURASI
                /* val intent = Intent(this@FinishActivity, MainActivity::class.java)
                 startActivity(intent)
                 finish()*/
            }
        }
        timer.start()

    }
}



