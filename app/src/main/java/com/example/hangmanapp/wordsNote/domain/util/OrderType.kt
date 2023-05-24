package com.example.hangmanapp.wordsNote.domain.util

sealed class OrderType{
  object Ascending: OrderType()
  object Descending: OrderType()
}
