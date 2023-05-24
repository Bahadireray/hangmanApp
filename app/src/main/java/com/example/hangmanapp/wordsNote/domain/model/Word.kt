package com.example.hangmanapp.wordsNote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hangmanapp.wordsNote.presentation.addWordsNote.WordUiState

@Entity
data class Word(
  val word: String,
  @PrimaryKey val id: Int? = null
)

class InvalidWordException(message: String) : Exception(message)
