package com.example.appnotes.di

import android.app.Application
import androidx.room.Room
import com.example.appnotes.data.cache.AppDatabase
import com.example.appnotes.data.cache.AppDatabase.Companion.DATABASE_NAME
import com.example.appnotes.data.cache.note.NoteDao
import com.example.appnotes.data.repositories.NoteRepository
import com.example.appnotes.ui.note_detail.ColorSelectorAdapter
import com.example.appnotes.ui.note_list.NoteListAdapter
import com.example.apppositive.R
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
    fun provideAppDataBase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    }

    @Provides
    @Singleton
    fun provideNoteDao(db: AppDatabase): NoteDao {
        return db.getNoteDao()
    }

    @Provides
    @Singleton
    fun provideNoteListAdapter() = NoteListAdapter()

    @Provides
    @Singleton
    fun provideColorSelectorAdapter() = ColorSelectorAdapter(
        listOf(
            R.color.app_bg_color,
            R.color.red001,
            R.color.pink002,
            R.color.orange003,
            R.color.orange004,
            R.color.yellow005,
            R.color.green006,
            R.color.green007,
            R.color.green008,
            R.color.blue009,
            R.color.blue010,
            R.color.purple011
        )
    )

    @Provides
    @Singleton
    fun provideNoteRepository() = NoteRepository()


}

