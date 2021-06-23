package com.example.typerace.Entitys

import androidx.room.Entity

@Entity(tableName = "word_table")
data class Word (
    val word : String)