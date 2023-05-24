package com.example.hangmanapp.wordsNote.domain.useCase

data class WordUseCases(
  val getWords: GetWords,
  val getWord: GetWord,
  val deleteWord: DeleteWord,
  val addWord: AddWord
)