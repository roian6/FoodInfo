package com.david0926.foodinfo.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStoreUtil @Inject constructor(@ApplicationContext appContext: Context) {
    private val Context.dataStore by preferencesDataStore(name = "datastore")

    companion object {
        val KEY_FIRST_LAUNCH = booleanPreferencesKey("first_launch")
    }

    val isFirstLaunch: Flow<Boolean> = appContext.dataStore.data.map { preferences ->
        preferences[KEY_FIRST_LAUNCH] ?: true
    }

    suspend fun afterFirstLaunch(context: Context) {
        context.dataStore.edit { preferences ->
            preferences[KEY_FIRST_LAUNCH] = false
        }
    }
}