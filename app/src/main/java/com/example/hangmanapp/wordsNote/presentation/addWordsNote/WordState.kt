package com.example.hangmanapp.wordsNote.presentation.addWordsNote

import com.example.hangmanapp.wordsNote.domain.model.Word
import com.example.hangmanapp.wordsNote.domain.util.OrderType
import com.example.hangmanapp.wordsNote.domain.util.WordOrder

data class WordState(
  var word: List<Word> = emptyList(),
  var wordOrder: WordOrder = WordOrder.Word(OrderType.Descending)
)
