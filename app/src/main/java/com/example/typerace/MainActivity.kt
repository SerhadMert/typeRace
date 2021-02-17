package com.example.typerace


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_settings.view.*


class MainActivity : AppCompatActivity() {

    lateinit var btGame: Button
    lateinit var btGlobal: Button
    lateinit var btProfile: ImageButton
    lateinit var btSettings: ImageButton
    lateinit var btHowToPlay: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportActionBar?.hide()


        btGame = findViewById(R.id.btGame)
        btGlobal = findViewById(R.id.btGlobal)
        btProfile = findViewById(R.id.btProfile)
        btSettings = findViewById(R.id.btSettings)
        btHowToPlay = findViewById(R.id.btHowToPlay)

        Thread {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            runOnUiThread {
                btGame.translationY = 1000f
                btGlobal.translationY = 1000f
                btProfile.translationY = -200f
                btSettings.translationY = -200f
                btHowToPlay.translationY=1000f

                btGame.animate()
                        .translationY(0f)
                        .setInterpolator(LinearInterpolator())
                        .setStartDelay(500)
                        .start()
                btGlobal.animate()
                        .translationY(0f)
                        .setInterpolator(LinearInterpolator())
                        .setStartDelay(500)
                        .start()
                btProfile.animate()
                        .translationY(0f)
                        .setInterpolator(LinearInterpolator())
                        .setStartDelay(500)
                        .start()
                btSettings.animate()
                        .translationY(0f)
                        .setInterpolator(LinearInterpolator())
                        .setStartDelay(500)
                        .start()
                btHowToPlay.animate()
                        .translationY(0f)
                        .setInterpolator(LinearInterpolator())
                        .setStartDelay(500)
                        .start()
                btGame.visibility = View.VISIBLE
                btGlobal.visibility = View.VISIBLE
                btProfile.visibility = View.VISIBLE
                btSettings.visibility =View.VISIBLE
                btHowToPlay.visibility =View.VISIBLE

                btGame.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(view: View?) {
                        startActivity(Intent(this@MainActivity, GameActivity::class.java))
                        
                    }
                })


                btProfile.setOnClickListener(object : View.OnClickListener{
                    override fun onClick(v: View?) {
                        startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
                    }

                })
                btSettings.setOnClickListener(object : View.OnClickListener{
                    override fun onClick(v: View?) {
                        val mDialogView = LayoutInflater.from(this@MainActivity).inflate(R.layout.activity_settings, null)
                        val mBuilder = AlertDialog.Builder(this@MainActivity)
                                .setView(mDialogView)
                                .setTitle("")

                        val  mAlertDialog = mBuilder.show()

                        mDialogView.button_cikis.setOnClickListener {
                            mAlertDialog.dismiss()
                        }
                    }

                })

                btHowToPlay.setOnClickListener(object : View.OnClickListener{
                    override fun onClick(v: View?) {
                        startActivity(Intent(this@MainActivity, HowToPlayActivity::class.java))
                    }

                })

            }
        }.start()


    }

}