package com.example.typerace


import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.typerace.Entitys.Word
import com.example.typerace.services.Firestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class TypeRaceRepository(private val wordDao: WordDao) {

    val allWords: List<Word> = wordDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAll(words: List<Word>) {
        wordDao.insertAll(words)
    }


    suspend fun getWords() {
        val firestore = Firestore()
        val db = firestore.getDatabase()
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

                    runBlocking {
                        launch {
                            insertAll(list)
                            println("World!")
                        }

                    }
                }
            }
    }
}