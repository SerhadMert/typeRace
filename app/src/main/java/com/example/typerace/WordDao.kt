package com.example.typerace

import androidx.room.*
import com.example.typerace.Entitys.Word


@Dao
interface WordDao {

    @Query("SELECT * FROM word_table")
    fun getAll(): List<Word>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Insert
    @JvmSuppressWildcards
    fun insertAll(words : List<Word>)
}