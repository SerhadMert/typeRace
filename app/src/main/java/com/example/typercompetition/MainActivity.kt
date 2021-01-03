package com.example.typercompetition

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var trueWord = 0
        var falseWord = 0

        val editText = findViewById<EditText>(R.id.compare_text)
        val scoreText = findViewById<TextView>(R.id.score)

        var textCompare: String = "selam"



        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                var text = editText.text
                //println(text.last())
                var isWhitespace = text.contains(" ")
                if (isWhitespace) {
                    text = text.dropLast(1) as Editable?
                    println("true")
                    println("-" + text + "-")
                    if (text.toString() == textCompare) {
                        trueWord++
                        editText.setText("")
                    } else {
                        falseWord++
                    }

                    scoreText.text = "Doğru " + trueWord + "-Yanlış " + falseWord

                }

            }

            override fun afterTextChanged(p0: Editable?) {

            }


        })


    }
}