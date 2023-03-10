package com.example.appnotes.data.data_store

import kotlinx.coroutines.flow.Flow

interface SettingDataStore {

    suspend fun toggleDarkMode()

    suspend fun toggleNotesLayout()

    fun readDarkModeValue(): Flow<Boolean>

    fun readNotesLayoutValue(): Flow<Boolean>
}