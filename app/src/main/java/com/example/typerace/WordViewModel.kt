package com.example.typerace

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.typerace.Entitys.Word
import kotlinx.coroutines.launch

class WordViewModel(private val repository: TypeRaceRepository) : ViewModel() {


    val allWords: List<Word> = repository.allWords


    fun insert(words: List<Word>) = viewModelScope.launch {
        repository.insertAll(words)
    }

    fun getWords() = viewModelScope.launch {
        repository.getWords()
    }
}

class WordViewModelFactory(private val repository: TypeRaceRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}