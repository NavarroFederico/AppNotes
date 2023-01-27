package com.example.appnotes.data.cache.note

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.DeleteTable
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note : NoteEntity) :Long
//Esta consulta , busca en la tabla de notes un texto ya sea en el contenido o en el titulo.Es un filtrado para un search.
    @Query("select * from notes where titulo like '%' || :query ||'%' or contenido like '%'|:query"   )
    suspend fun getNotes(query: String): List<NoteEntity>

   @Query("delete from notes  ")
   suspend fun deleteTable()

}