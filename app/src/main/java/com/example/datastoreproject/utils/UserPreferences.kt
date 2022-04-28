package com.example.datastoreproject.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(
    context: Context
) {
    private val applicationContext = context
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")



    val randomText: Flow<String?>
        get() = applicationContext.dataStore.data.map { preferences ->
            preferences[KEY_BOOKMARK]
        }

    suspend fun saveRandomText(bookmark: String) {
        applicationContext.dataStore.edit { preferences ->
            preferences[KEY_BOOKMARK] = bookmark
        }
    }


    companion object {
        val KEY_BOOKMARK = stringPreferencesKey("key_text")
    }
}