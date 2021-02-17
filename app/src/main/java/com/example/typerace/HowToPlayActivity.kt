package com.example.typerace

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HowToPlayActivity : AppCompatActivity (){

    var isFragmentOneLoaded = true
    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_howtoplay)

        val Change = findViewById<Button>(R.id.button_change)
        val Quit = findViewById<Button>(R.id.button_cikis)
        ShowFragmentOne()
        Change.setOnClickListener {
            if (isFragmentOneLoaded)
                ShowFragmentTwo()
            else
                ShowFragmentOne()
        }
         Quit.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                startActivity(Intent(this@HowToPlayActivity, MainActivity::class.java))

            }
        })

    }
    fun ShowFragmentOne(){
        val transaction = manager.beginTransaction();
        val fragment= FragmentOne()
        transaction.replace(R.id.fragment_holder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragmentOneLoaded=true
    }
    fun ShowFragmentTwo(){
        val transaction = manager.beginTransaction();
        val fragment= FragmentTwo()
        transaction.replace(R.id.fragment_holder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragmentOneLoaded=false
    }
}