package com.example.appnotes.data.repositories.utils

import androidx.datastore.preferences.core.booleanPreferencesKey

class DataStoreKeys {

    companion object{
        val DARK_MODE_KEY = booleanPreferencesKey("com.example.appnotes.DARK_MODE_KEY")
        val LAYOUT_MODE_KEY = booleanPreferencesKey("com.example.appnotes.LAYOUT_MODE_KEY")
    }
}