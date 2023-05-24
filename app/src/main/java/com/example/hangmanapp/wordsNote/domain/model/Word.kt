package com.example.hangmanapp.wordsNote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
  val word: String,
  @PrimaryKey val id: Int? = null
)

class InvalidWordException(message: String) : Exception(message)
