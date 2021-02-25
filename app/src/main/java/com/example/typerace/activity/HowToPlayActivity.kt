package com.example.typerace.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.typerace.fragment.FragmentOne
import com.example.typerace.fragment.FragmentTwo
import com.example.typerace.R

class HowToPlayActivity : AppCompatActivity (){

    private var isFragmentOneLoaded = true
    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_howtoplay)

        val change = findViewById<Button>(R.id.button_change)
        val quit = findViewById<Button>(R.id.button_cikis)
        showFragmentOne()
        change.setOnClickListener {
            if (isFragmentOneLoaded)
                showFragmentTwo()
            else
                showFragmentOne()
        }
        quit.setOnClickListener { startActivity(Intent(this@HowToPlayActivity, MainActivity::class.java)) }

    }
    private fun showFragmentOne(){
        val transaction = manager.beginTransaction()
        val fragment= FragmentOne()
        transaction.replace(R.id.fragment_holder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragmentOneLoaded=true
    }
    private fun showFragmentTwo(){
        val transaction = manager.beginTransaction()
        val fragment= FragmentTwo()
        transaction.replace(R.id.fragment_holder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragmentOneLoaded=false
    }
}