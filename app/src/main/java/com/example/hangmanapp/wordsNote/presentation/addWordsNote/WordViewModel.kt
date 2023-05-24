package com.example.hangmanapp.wordsNote.presentation.addWordsNote


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hangmanapp.wordsNote.domain.model.Word
import com.example.hangmanapp.wordsNote.domain.useCase.WordUseCases
import com.example.hangmanapp.wordsNote.domain.util.OrderType
import com.example.hangmanapp.wordsNote.domain.util.WordOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
  private val wordUseCases: WordUseCases
) : ViewModel() {

  private val _state = MutableStateFlow(WordState())
  private var state: StateFlow<WordState> = _state

  private var recentlyDeletedWord: Word? = null

  private var getWordJob: Job? = null

  init {
    getWord(WordOrder.Word(OrderType.Descending))
  }

  suspend fun onEvent(event: WordEvent) {
    when (event) {
      is WordEvent.Order -> {

        if (state.value.wordOrder == event.wordOrder &&
          state.value.wordOrder.orderType == event.wordOrder.orderType
        ) {
          return
        }
        getWord(event.wordOrder)
      }

      is WordEvent.DeleteWord -> {
        viewModelScope.launch {
          wordUseCases.deleteWord(event.word)
          recentlyDeletedWord = event.word
        }
      }
    }
  }

  private fun getWord(wordOrder: WordOrder) {
    getWordJob?.cancel()
    getWordJob = wordUseCases.getWord(wordOrder)
      .onEach { word ->
        _state.value = state.value.copy(
          word = word,
          wordOrder = wordOrder
        )
      }
      .launchIn(viewModelScope)
  }
}