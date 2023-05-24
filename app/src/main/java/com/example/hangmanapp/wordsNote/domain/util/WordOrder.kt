package com.example.hangmanapp.wordsNote.domain.util

sealed class WordOrder(val orderType: OrderType){
  class Word(orderType: OrderType):WordOrder(orderType)
}
