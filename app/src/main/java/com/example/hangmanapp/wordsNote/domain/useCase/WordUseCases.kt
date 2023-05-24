package com.example.hangmanapp.wordsNote.domain.useCase

import com.example.hangmanapp.wordsNote.domain.repository.AddWord

data class WordUseCases(
  val getWord: GetWord,
  val deleteWord: DeleteWord,
  val addWord: AddWord
)