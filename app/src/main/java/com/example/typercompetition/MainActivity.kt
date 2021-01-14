package com.example.typercompetition


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {

    lateinit var btGame: Button
    lateinit var btGlobal: Button
    lateinit var consss: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportActionBar?.hide()


        btGame = findViewById(R.id.btGame)
        btGlobal = findViewById(R.id.btGlobal)

        Thread {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            runOnUiThread {
                btGame.translationY = 1000f
                btGlobal.translationY = 1000f

                btGame.animate()
                        .translationY(0f)
                        .setInterpolator(LinearInterpolator())
                        .setStartDelay(1000)
                        .start()
                btGlobal.animate()
                        .translationY(0f)
                        .setInterpolator(LinearInterpolator())
                        .setStartDelay(1000)
                        .start()

                btGame.visibility = View.VISIBLE
                btGlobal.visibility = View.VISIBLE


                btGame.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(view: View?) {
                        startActivity(Intent(this@MainActivity, GameActivity::class.java))
                    }
                })
            }
        }.start()


    }

}