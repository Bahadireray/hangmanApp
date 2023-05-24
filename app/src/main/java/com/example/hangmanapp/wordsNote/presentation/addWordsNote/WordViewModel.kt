package com.example.hangmanapp.wordsNote.presentation.addWordsNote


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hangmanapp.wordsNote.domain.model.InvalidWordException
import com.example.hangmanapp.wordsNote.domain.model.Word
import com.example.hangmanapp.wordsNote.domain.useCase.WordUseCases
import com.example.hangmanapp.wordsNote.domain.util.OrderType
import com.example.hangmanapp.wordsNote.domain.util.WordOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
  private val wordUseCases: WordUseCases,
  savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val _state = MutableStateFlow(WordState())
  private var state: StateFlow<WordState> = _state

  private val _eventFlow = MutableSharedFlow<WordEvent>()
  val eventFlow = _eventFlow.asSharedFlow()

  private val _addWord= mutableStateOf(WordUiState())
  private val addWord:State<WordUiState> = _addWord

  private var recentlyDeletedWord: Word? = null

  private var getWordJob: Job? = null

  private var currentWordId: Int? = null

  init {
    getWord(WordOrder.Word(OrderType.Descending))
    savedStateHandle.get<Int>("wordId")?.let { wordId ->
      if (wordId != -1){
        viewModelScope.launch {
          wordUseCases.getWord(wordId)?.also { saveWord->
            currentWordId=saveWord.id
            _addWord.value=addWord.value.copy(
              text = saveWord.word
            )

          }
        }
      }

    }
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

      is WordEvent.RestoreNote -> {
        viewModelScope.launch {
          wordUseCases.addWord(recentlyDeletedWord ?: return@launch)
          recentlyDeletedWord = null
        }
      }

      is WordEvent.SaveWord -> {
        viewModelScope.launch {
          try {
            wordUseCases.addWord(
              Word(
                word = addWord.value.text,
                id = currentWordId
              )
            )
            _eventFlow.emit(WordEvent.SaveWord)
          } catch (e: InvalidWordException) {

            _eventFlow.emit(
              WordEvent.ShowSnackBar(
                message = e.message ?: "Not Save"
              )
            )
          }
        }
      }

      else -> {}
    }
  }

  private fun getWord(wordOrder: WordOrder) {
    getWordJob?.cancel()
    getWordJob = wordUseCases.getWords(wordOrder)
      .onEach { word ->
        _state.value = state.value.copy(
          word = word,
          wordOrder = wordOrder
        )
      }
      .launchIn(viewModelScope)
  }
}