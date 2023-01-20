package com.example.appnotes.data.repositories

import com.example.appnotes.data.cache.note.NoteDao
import com.example.appnotes.data.cache.note.NoteEntity
import com.example.appnotes.data.cache.note.toNoteEntity
import com.example.appnotes.data.data_source.getNoteList
import com.example.appnotes.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class NoteRepository(private val noteDao: NoteDao) {

    fun getNotesPorDefecto(): Flow<List<Note>> = flow {
        val cacheNoteList = getNoteList()
        emit(cacheNoteList)

    }.catch { e ->
        e.printStackTrace()
    }

    fun insertNote(note: Note): Flow<Boolean> = flow {
        noteDao.insertNote(note.toNoteEntity())
        emit(true)
    }.catch { e ->
        e.printStackTrace()
    }
}