package com.example.typercompetition

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


const val SCORE_MESSAGE_T = "com.example.typercompetition.SCORE_T"
const val SCORE_MESSAGE_F = "com.example.typercompetition.SCORE_F"

class GameActivity : AppCompatActivity() {

    var textCompare: String =
            "Lorem ipsum dolor sit amet consectetur adipiscing elit Nullam cursus nisl quis tortor aliquam vitae posuere risus lobortis " +
                    "Phasellus sed augue neque Praesent semper tortor lorem ac aliquet nisl vulputate ut Duis volutpat condimentum nunc a commodo " +
                    "Praesent venenatis metus lorem, et interdum risus tincidunt eu. Nullam fringilla vel tortor id auctor " +
                    "Maecenas egestas nulla at consectetur posuere Integer eget lectus et lacus imperdiet fringilla Donec ut ex in erat consequat auctor " +
                    "Aliquam metus lorem tristique non metus ac laoreet sodales quam Phasellus hendrerit lorem justo eget scelerisque arcu posuere ac " +
                    "Integer porta dui non mattis iaculis arcu enim convallis"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)


        var trueWord = 0
        var falseWord = 0

        val editText = findViewById<EditText>(R.id.compare_text)
        val scoreText = findViewById<TextView>(R.id.score)
        val mainText = findViewById<TextView>(R.id.main_text)
        val timerText = findViewById<TextView>(R.id.timer_text)

        val color = mainText.currentTextColor


        //show text and array convert
        mainText.text = textCompare

        var strs = textCompare.split(" ").toTypedArray()
        mainText.text = spanText(strs, color, strs[0].length)



        //timer
        val timer = object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerText.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                val intent = Intent(this@GameActivity, FinishActivity::class.java).apply {
                    putExtra(SCORE_MESSAGE_T, trueWord.toString())
                    putExtra(SCORE_MESSAGE_F, falseWord.toString())
                }
                startActivity(intent)
                finish()
            }
        }
        timer.start()

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


                if (strs.size > 0) {
                    var text = editText.text
                    var deneme = text.toString().replace(" ", "")
                    if (strs[0].substring(0, deneme.length) == deneme) {
                        mainText.text = spanText(strs, Color.GREEN, deneme.length)
                    } else {
                        mainText.text = spanText(strs, Color.RED, deneme.length)
                    }


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
                        mainText.text = spanText(strs, color, strs[0].length)
                        scoreText.text = "Doğru " + trueWord + " -Yanlış " + falseWord

                    }
                }

            }

            override fun afterTextChanged(p0: Editable?) {

            }


        })
    }

    fun spanText(strs: Array<String>, color: Int, strLength: Int): Spannable {
        val spannable = SpannableString(strs.joinToString().replace(",", ""))
        spannable.setSpan(ForegroundColorSpan(color),
                0, strLength,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(
                RelativeSizeSpan(1.1f),
                0, strs[0].length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                0, strs[0].length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        return spannable
    }
}