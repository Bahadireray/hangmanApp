package com.example.hangmanapp.wordsNote.domain.useCase

import com.example.hangmanapp.wordsNote.domain.model.Word
import com.example.hangmanapp.wordsNote.domain.repository.WordRepository

class DeleteWord(
  private val repository: WordRepository
) {
  suspend operator fun invoke(word: Word) {
    repository.deleteWord(word)
  }
}