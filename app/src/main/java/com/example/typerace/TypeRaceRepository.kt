package com.example.typerace

import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.typerace.Entitys.Word
import com.example.typerace.services.Firestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Flow

class TypeRaceRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: List<Word> = wordDao.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAll(words: List<Word>) {
        wordDao.insertAll(words)
    }


    suspend fun getWords() {
        val firestore = Firestore()
        val db = firestore.getDatabase()
        var returnList = MutableLiveData<List<Word>>()
        var list = ArrayList<Word>()

        db.collection("data").document("words").get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("ffirebase", "DocumentSnapshot data : ${task.result?.data}")

                    task.result?.data?.forEach { item ->

                        val itemValue = item.value.toString()
                        val word = Word(itemValue)

                        list.add(word)
                    }

                    runBlocking { // this: CoroutineScope
                        launch { // launch a new coroutine and continue
                            insertAll(list) // non-blocking delay for 1 second (default time unit is ms)
                            println("World!") // print after delay
                        }

                    }
                }
            }
    }
}