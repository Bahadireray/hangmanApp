package com.example.hangmanapp.wordsNote.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hangmanapp.wordsNote.domain.model.Word

@Database(
  entities = [Word::class],
  version = 1
)
abstract class WordDatabase:RoomDatabase() {

  abstract val wordsDao: WordsDao

  companion object{
    const val DATABASE_NAME = "word_db"
  }

}