package com.example.typercompetition

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val textView = findViewById<TextView>(R.id.textView)
        val trueWord = Integer.parseInt(intent.getStringExtra(SCORE_MESSAGE_T))
        val falseWord = Integer.parseInt(intent.getStringExtra(SCORE_MESSAGE_F))



        textView.text =
            "Score T=" + trueWord + "F=" + falseWord + "Toplam = " + (trueWord + falseWord)
        val timer = object : CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                val intent = Intent(this@FinishActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        timer.start()

    }
}



