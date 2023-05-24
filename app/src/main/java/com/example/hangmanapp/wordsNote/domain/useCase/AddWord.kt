package com.example.hangmanapp.wordsNote.domain.useCase

import com.example.hangmanapp.wordsNote.domain.model.InvalidWordException
import com.example.hangmanapp.wordsNote.domain.model.Word
import com.example.hangmanapp.wordsNote.domain.repository.WordRepository
import kotlin.jvm.Throws

class AddWord(
  private val repository: WordRepository
) {
  @Throws(InvalidWordException::class)
  suspend operator fun invoke(word: Word) {
    if (word.word.isBlank()) {
      throw InvalidWordException("Is Empty.")
    }
    repository.insertWord(word)
  }
}
