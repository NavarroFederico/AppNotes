package com.example.appnotes.data.data_source

import com.example.appnotes.domain.model.Note
import com.example.apppositive.R

fun getNoteList() = listOf(
    Note(
        title = "Nota por defecto 1", content = "Contenido de la nota número 1"
    ),
    Note(
        title = "Nota por defecto 2", content = "Contenido de la nota número 2", color = R.color.blue009
    ),
    Note(
        title = "Nota por defecto 3", content = "Contenido de la nota número 3"
    ),
    Note(
        title = "Nota por defecto 4", content = "Contenido de la nota número 4"
    ),
    Note(
        title = "Nota por defecto 5", content = "Contenido de la nota número 5",color = R.color.purple011
    ),
    Note(
        title = "Nota por defecto 6", content = "Contenido de la nota número 6", color = R.color.orange004
    ),
    Note(
        title = "Nota por defecto 7", content = "Contenido de la nota número 7"
    ),
    Note(
        title = "Nota por defecto 8", content = "Contenido de la nota número 8"
    ),
    Note(
        title = "Nota por defecto 9", content = "Contenido de la nota número 9"
    )
)