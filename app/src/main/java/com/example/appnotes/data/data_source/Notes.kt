package com.example.appnotes.data.data_source

import com.example.appnotes.domain.model.Note
import com.example.apppositive.R

fun getNoteList() = listOf(
    Note(
        title = "Titulo 1", content = "Contenido de la nota número 1"
    ),
    Note(
        title = "Titulo 2", content = "Contenido de la nota número 2", color = R.color.blue009
    ),
    Note(
        title = "Titulo 3", content = "Contenido de la nota número 3"
    ),
    Note(
        title = "Titulo 4", content = "Contenido de la nota número 4"
    ),
    Note(
        title = "Titulo 5", content = "Contenido de la nota número 5",color = R.color.purple011
    ),
    Note(
        title = "Titulo 6", content = "Contenido de la nota número 6", color = R.color.orange004
    ),
    Note(
        title = "Titulo 7", content = "Contenido de la nota número 7"
    ),
    Note(
        title = "Titulo 8", content = "Contenido de la nota número 8"
    ),
    Note(
        title = "Titulo 9", content = "Contenido de la nota número 9"
    )
)