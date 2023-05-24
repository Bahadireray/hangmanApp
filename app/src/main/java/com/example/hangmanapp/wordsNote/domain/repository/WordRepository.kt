package com.example.hangmanapp.wordsNote.domain.repository

import com.example.hangmanapp.wordsNote.domain.model.Word
import kotlinx.coroutines.flow.Flow


interface WordRepository {
  fun getWord(): Flow<List<Word>>

  suspend fun getWordById(id: Int): Word?

  suspend fun insertWord(word: Word)

  suspend fun deleteWord(word: Word)
}