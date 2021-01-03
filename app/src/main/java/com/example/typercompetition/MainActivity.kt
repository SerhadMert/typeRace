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
        val mainText = findViewById<TextView>(R.id.main_text)

        var textCompare: String = "Lorem ipsum dolor sit amet consectetur adipiscing elit Nullam cursus nisl quis tortor aliquam vitae posuere risus lobortis " +
                "Phasellus sed augue neque Praesent semper tortor lorem ac aliquet nisl vulputate ut Duis volutpat condimentum nunc a commodo " +
                "Praesent venenatis metus lorem, et interdum risus tincidunt eu. Nullam fringilla vel tortor id auctor " +
                "Maecenas egestas nulla at consectetur posuere Integer eget lectus et lacus imperdiet fringilla Donec ut ex in erat consequat auctor " +
                "Aliquam metus lorem tristique non metus ac laoreet sodales quam Phasellus hendrerit lorem justo eget scelerisque arcu posuere ac " +
                "Integer porta dui non mattis iaculis arcu enim convallis"

        mainText.text = textCompare

        var strs = textCompare.split(" ").toTypedArray()

        mainText.text = strs.joinToString().replace(",", "")




        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                var text = editText.text
                if (strs.size > 0) {
                    var isWhitespace = text.contains(" ")
                    if (isWhitespace) {
                        text = text.dropLast(1) as Editable?
                        println("true")
                        println("-" + text + "-")
                        if (text.toString() == strs[0]) {
                            trueWord++
                            editText.setText("")
                        } else {
                            falseWord++
                            editText.setText("")
                        }
                        strs = strs.drop(1).toTypedArray()
                        mainText.text = strs.joinToString().replace(",", "")
                        scoreText.text = "Doğru " + trueWord + "-Yanlış " + falseWord

                    }
                }

            }

            override fun afterTextChanged(p0: Editable?) {

            }


        })


    }
}