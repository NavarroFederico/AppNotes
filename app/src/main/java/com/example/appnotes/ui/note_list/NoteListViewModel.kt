package com.example.appnotes.ui.note_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnotes.data.repositories.NoteRepository
import com.example.appnotes.data.repositories.SettingsRepository
import com.example.appnotes.domain.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel
@Inject
constructor(
    private val noteRepository: NoteRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    private var _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList: StateFlow<List<Note>> = _noteList

    private var _darkMode = MutableStateFlow<Boolean>(false)
    val darkMode : StateFlow<Boolean> = _darkMode

    private var _linearLayoutMode = MutableStateFlow<Boolean>(false)
    val linearLayoutMode : StateFlow<Boolean> = _linearLayoutMode

    private var _searchQuery = MutableStateFlow("")

    init {
        getNotes()
        getSettings()
    }


    fun getNotes(){
        noteRepository.getNotes(_searchQuery.value).onEach { noteList ->
            _noteList.value = noteList
        }.launchIn(viewModelScope)
    }

    fun updateQuery(newQuery: String){
        _searchQuery.value = newQuery
        getNotes()
    }

    fun toggleDarkMode(){
       viewModelScope.launch {
           settingsRepository.toggleDarkMode()
       }
    }
    fun toggleLayoutMode(){
        viewModelScope.launch {
            settingsRepository.toggleNotesLayout()
        }
    }
    private fun getSettings(){
        settingsRepository.readDarkModeValue().onEach { isDarkMode->
            _darkMode.value = isDarkMode
        }.launchIn(viewModelScope)

        settingsRepository.readNotesLayoutValue().onEach { isLinearLayout->
            _linearLayoutMode.value = isLinearLayout
        }.launchIn(viewModelScope)
    }
}


