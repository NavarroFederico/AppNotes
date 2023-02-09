package com.example.appnotes.data.cache.note

import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity): Long

    //Esta consulta , busca en la tabla de notes un texto ya sea en el contenido o en el titulo.Es un filtrado para un search.
    @Query("select * from notes where titulo like '%' || :query ||'%' or contenido like '%'|:query")
    suspend fun getNotes(query: String): List<NoteEntity>


    @Query("SELECT * FROM notes WHERE id= :noteId")
    suspend fun getNoteById(noteId: String) : NoteEntity?

    @Query("delete from notes  ")
    suspend fun deleteTable()

    @Update
    suspend fun updateNote(note: NoteEntity)
}