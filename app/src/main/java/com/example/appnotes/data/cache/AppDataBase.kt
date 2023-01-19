package com.example.appnotes.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appnotes.data.cache.note.NoteDao
import com.example.appnotes.data.cache.note.NoteEntity

@Database(entities = [NoteEntity::class],version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

//    companion object{
//        val DATABASE_NAME = "notes_db"
//    }
}