package com.example.hangmanapp.wordsNote.data.dataSource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hangmanapp.wordsNote.domain.model.Word
import kotlinx.coroutines.flow.Flow


@Dao
interface WordsDao {

  @Query("SELECT * FROM word")
  fun getWord(): Flow<List<Word>>

  @Query("SELECT * FROM word WHERE id=:id")
  suspend fun getWordById(id: Int): Word?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertWord(word: Word)

  @Delete
  suspend fun deleteWord(word: Word)
}