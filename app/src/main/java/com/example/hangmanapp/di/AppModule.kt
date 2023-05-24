package com.example.hangmanapp.di

import android.app.Application
import androidx.room.Room
import com.example.hangmanapp.wordsNote.data.dataSource.WordDatabase
import com.example.hangmanapp.wordsNote.data.repository.WordRepositoryImpl
import com.example.hangmanapp.wordsNote.domain.useCase.AddWord
import com.example.hangmanapp.wordsNote.domain.repository.WordRepository
import com.example.hangmanapp.wordsNote.domain.useCase.DeleteWord
import com.example.hangmanapp.wordsNote.domain.useCase.GetWord
import com.example.hangmanapp.wordsNote.domain.useCase.GetWords
import com.example.hangmanapp.wordsNote.domain.useCase.WordUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  @Provides
  @Singleton
  fun provideWordDataBase(app: Application): WordDatabase {
    return Room.databaseBuilder(
      app,
      WordDatabase::class.java,
      WordDatabase.DATABASE_NAME
    ).build()
  }

  @Provides
  @Singleton
  fun provideWordRepository(db: WordDatabase): WordRepository {
    return WordRepositoryImpl(db.wordsDao)
  }

  @Provides
  @Singleton
  fun provideWordUseCases(repository: WordRepository): WordUseCases {
    return WordUseCases(
      getWords = GetWords(repository),
      deleteWord = DeleteWord(repository),
      addWord = AddWord(repository),
      getWord = GetWord(repository)
    )
  }

}