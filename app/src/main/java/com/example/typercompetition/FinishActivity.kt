package com.example.typercompetition

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



        textView.text = "Score T=" + trueWord + "F=" + falseWord + "Toplam = " + (trueWord + falseWord)

        //timer 10 saniye sonra main activitye gidicek

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



