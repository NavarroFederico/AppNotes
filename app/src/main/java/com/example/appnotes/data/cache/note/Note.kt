package com.example.appnotes.data.cache.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appnotes.domain.model.Note
import com.example.apppositive.R
import java.util.*

@Entity(tableName = "notes")

data class NoteEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "titulo")
    val title: String,
    @ColumnInfo(name = "contenido")
    val content: String,
    @ColumnInfo(name = "color")
    val color: Int = R.color.app_bg_color,
    @ColumnInfo(name = "f_creado")
    val created: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "f_actualizado")
    val updated: Long = System.currentTimeMillis()
)

fun NoteEntity.toNote(): Note {
    return Note(
        id = id,
        title = title,
        content = content,
        color = color,
        created = created,
        updated = updated
    )
}

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity( id = id,
        title = title,
        content = content,
        color = color,
        created = created,
        updated = updated)
}