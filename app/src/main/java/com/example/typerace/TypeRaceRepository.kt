package com.example.typerace

import androidx.annotation.WorkerThread
import com.example.typerace.Entitys.Word
import java.util.concurrent.Flow

class TypeRaceRepository (private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: List<Word> = wordDao.getAll()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(words: List<Word>) {
        wordDao.insertAll(words)
    }
}