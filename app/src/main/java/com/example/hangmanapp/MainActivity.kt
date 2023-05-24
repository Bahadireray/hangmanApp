package com.example.hangmanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hangmanapp.ui.theme.HangmanAppTheme
import com.example.hangmanapp.wordsNote.presentation.addWordsNote.WordScrenn
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      HangmanAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

          Spacer(modifier = Modifier.padding(10.dp))
          WordScrenn()
        }
      }
    }
  }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  HangmanAppTheme {

  }
}