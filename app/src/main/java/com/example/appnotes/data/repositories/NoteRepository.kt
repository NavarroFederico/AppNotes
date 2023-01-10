package com.example.appnotes.data.repositories

import com.example.appnotes.data.data_source.getNoteList
import com.example.appnotes.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class NoteRepository {

    fun getnotes(): Flow<List<Note>> = flow{
        val cacheNoteList = getNoteList()
        emit(cacheNoteList)

    }.catch { e ->
        e.printStackTrace()
    }
}