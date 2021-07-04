package com.example.typerace.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.typerace.R
import com.example.typerace.fragment.*

class HowToPlayActivity : AppCompatActivity() {

    private var isFragmentOneLoaded = true
    private var isFragmentCorrectLoaded = true
    private var isFragmentFalseLoaded = true
    private var isFragmentPauseLoaded = true
    private var isFragmentFinishLoaded = true
    private var isFragmentRankListLoaded = true
    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_howtoplay)

        val change = findViewById<Button>(R.id.button_change)
        val quit = findViewById<Button>(R.id.button_cikis)
        showFragmentOne()
        change.setOnClickListener {
            if (isFragmentOneLoaded)
                showFragmentCorrect()
            else if (isFragmentCorrectLoaded)
                showFragmentFalse()
            else if (isFragmentFalseLoaded)
                showFragmentPause()
            else if (isFragmentPauseLoaded)
                showFragmentFinish()
            else if (isFragmentFinishLoaded)
                showFragmentRankList()
            else showFragmentOne()
        }
        quit.setOnClickListener {
            startActivity(
                Intent(
                    this@HowToPlayActivity,
                    MainActivity::class.java
                )
            )
        }

    }

    private fun showFragmentOne() {
        val transaction = manager.beginTransaction()
        val fragment = FragmentOne()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragmentOneLoaded = true
    }

    private fun showFragmentCorrect() {
        val transaction = manager.beginTransaction()
        val fragment = FragmentCorrect()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragmentCorrectLoaded = true
        isFragmentOneLoaded = false
    }

    private fun showFragmentFalse() {
        val transaction = manager.beginTransaction()
        val fragment = FragmentFalse()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragmentFalseLoaded = true
        isFragmentCorrectLoaded = false
    }

    private fun showFragmentPause() {
        val transaction = manager.beginTransaction()
        val fragment = FragmentPauseMenu()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragmentPauseLoaded = true
        isFragmentFalseLoaded = false
    }

    private fun showFragmentFinish() {
        val transaction = manager.beginTransaction()
        val fragment = FragmentFinishActivity()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragmentFinishLoaded = true
        isFragmentPauseLoaded = false
    }

    private fun showFragmentRankList() {
        val transaction = manager.beginTransaction()
        val fragment = FragmentRankList()
        transaction.replace(R.id.fragment_holder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
        isFragmentRankListLoaded = true
        isFragmentFinishLoaded = false
    }
}