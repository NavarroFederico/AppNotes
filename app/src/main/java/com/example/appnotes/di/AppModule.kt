package com.example.appnotes.di

import com.example.appnotes.data.repositories.NoteRepository
import com.example.appnotes.ui.note_list.NoteListAdapter
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
    fun provideNoteListAdapter() = NoteListAdapter()

    @Provides
    @Singleton
    fun provideNoteRepository() = NoteRepository()


}

