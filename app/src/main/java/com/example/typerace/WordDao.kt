package com.example.typerace

import androidx.room.*
import com.example.typerace.Entitys.Word


@Dao
interface WordDao {

    @Query("SELECT * FROM word_table")
    fun getAll(): List<Word>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)

    @Query("DELETE FROM word_table")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(words : List<Word>)
}