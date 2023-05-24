package com.example.hangmanapp.wordsNote.domain.useCase

import com.example.hangmanapp.wordsNote.domain.model.Word
import com.example.hangmanapp.wordsNote.domain.repository.WordRepository

class GetWord(
  private val repository: WordRepository
) {
  suspend operator fun invoke(id: Int): Word? {
    return repository.getWordById(id)
  }
}