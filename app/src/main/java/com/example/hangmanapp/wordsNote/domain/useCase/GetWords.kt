package com.example.hangmanapp.wordsNote.domain.useCase

import com.example.hangmanapp.wordsNote.domain.model.Word
import com.example.hangmanapp.wordsNote.domain.repository.WordRepository
import com.example.hangmanapp.wordsNote.domain.util.OrderType
import com.example.hangmanapp.wordsNote.domain.util.WordOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetWords(
  private val repository: WordRepository
) {
  operator fun invoke(
    wordOrder: WordOrder = WordOrder.Word(OrderType.Descending)
  ): Flow<List<Word>> {
    return repository.getWord().map { word ->
      when (wordOrder.orderType) {
        is OrderType.Ascending -> {
          word.sortedBy { it.word.lowercase() }
        }

        is OrderType.Descending -> {
          word.sortedByDescending { it.word.lowercase() }
        }
      }
    }
  }
}