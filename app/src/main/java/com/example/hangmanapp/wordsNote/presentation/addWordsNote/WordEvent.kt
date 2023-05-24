package com.example.hangmanapp.wordsNote.presentation.addWordsNote

import com.example.hangmanapp.wordsNote.domain.model.Word
import com.example.hangmanapp.wordsNote.domain.util.WordOrder

sealed class WordEvent {
  data class Order(val wordOrder: WordOrder) : WordEvent()
  data class DeleteWord(val word: Word) : WordEvent()
}
