package com.example.hangmanapp.wordsNote.data.repository

import com.example.hangmanapp.wordsNote.data.dataSource.WordsDao
import com.example.hangmanapp.wordsNote.domain.model.Word
import com.example.hangmanapp.wordsNote.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow

class WordRepositoryImpl(
  private val dao: WordsDao
) : WordRepository {

  override fun getWord(): Flow<List<Word>> {
    return dao.getWord()
  }

  override suspend fun getWordById(id: Int): Word? {
    return dao.getWordById(id)
  }

  override suspend fun insertWord(word: Word) {
    return dao.insertWord(word)
  }

  override suspend fun deleteWord(word: Word) {
    return dao.deleteWord(word)
  }
}