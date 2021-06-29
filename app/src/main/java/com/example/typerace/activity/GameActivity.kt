package com.example.typerace.activity


import android.annotation.SuppressLint
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
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.typerace.Entitys.Word
import com.example.typerace.R
import com.example.typerace.TypeRaceApplication
import com.example.typerace.WordViewModel
import com.example.typerace.WordViewModelFactory
import kotlinx.android.synthetic.main.layout_popup_menu.view.*


const val SCORE_MESSAGE_T = "com.example.typercompetition.SCORE_T"
const val SCORE_MESSAGE_F = "com.example.typercompetition.SCORE_F"

class GameActivity : AppCompatActivity() {

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as TypeRaceApplication).repository)
    }

    var trueWord = 0
    var falseWord = 0
    var currentMillis = 0L
    private lateinit var timer: CountDownTimer
    lateinit var timerText: TextView
    lateinit var scoreText: TextView
    lateinit var mainText: TextView
    lateinit var editText: EditText




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        supportActionBar?.title = ""


        editText = findViewById(R.id.compare_text)
        scoreText = findViewById(R.id.score)
        mainText = findViewById(R.id.main_text)
        timerText = findViewById(R.id.timer_text)

        var textCompare =setWords(wordViewModel.allWords)
        

        val color = mainText.currentTextColor

        //show text and array convert
        mainText.text = textCompare

        var splitText: Array<String> = textCompare.split(" ").toTypedArray()
        mainText.text = spanText(splitText, color, splitText[0].length)



        //timer
        timerMet(60000)


        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            @SuppressLint("SetTextI18n")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (splitText.isNotEmpty()) {
                    var text = editText.text
                    val deneme = "$text".replace(" ", "")
                    if (splitText[0].length >= deneme.length) {
                        if (splitText[0].substring(0, deneme.length) == deneme) {
                            mainText.text = spanText(splitText, Color.GREEN, deneme.length)
                        } else {
                            mainText.text = spanText(splitText, Color.RED, deneme.length)
                        }
                    } else {
                        mainText.text = spanText(splitText, Color.RED, splitText[0].length)
                    }

                    val isWhitespace = text.contains(" ")
                    if (isWhitespace) {
                        text = text.dropLast(1) as Editable?
                        if (text.toString() == splitText[0]) {
                            trueWord++
                            editText.text.clear()
                        } else {
                            falseWord++
                            editText.text.clear()
                        }
                        splitText = splitText.drop(1).toTypedArray()
                        mainText.text = spanText(splitText, color, splitText[0].length)
                        scoreText.text = "Doğru $trueWord-Yanlış $falseWord"
                    }
                }
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

    }

    private fun setWords(words :List<Word>) : String{
        var returnString = ""
        var randomList = words.shuffled()
        randomList.forEach { item ->
            returnString +=item.word+" "
        }
        return returnString
    }

    private fun timerMet(time: Long) {
        timer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentMillis = millisUntilFinished
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
    }


    fun spanText(strs: Array<String>, color: Int, strLength: Int): Spannable {
        val spannable = SpannableString(strs.joinToString().replace(",", ""))
        spannable.setSpan(
            ForegroundColorSpan(color),
            0, strLength,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            RelativeSizeSpan(1.15f),
            0, strs[0].length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            0, strs[0].length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannable
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game, menu)
        return true
    }

    @Suppress("NAME_SHADOWING")
    @SuppressLint("WrongConstant")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.pause_button -> {
                //pause game
                timer.cancel()


                //CUSTOM ALERTDİALOG

                //XML DOSYASININ ADI layout_popup_menu.xml Onun tasarımı yapıalcak
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.layout_popup_menu, null)

                val mBuilder = AlertDialog.Builder(this,R.style.Theme_D1NoTitleDim)
                    .setView(mDialogView)
                    .setTitle("")

                val  mAlertDialog = mBuilder.show()

                mDialogView.button.setOnClickListener {

                    mAlertDialog.dismiss()
                    timerMet(currentMillis)

                }

                mDialogView.button2.setOnClickListener {

                    val intent=Intent(this@GameActivity, GameActivity::class.java)
                    startActivity(intent)
                    finish()
                }



                mDialogView.button4.setOnClickListener {
                    val intent=Intent(this@GameActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }





            }


        }
        return super.onOptionsItemSelected(item)
    }
}