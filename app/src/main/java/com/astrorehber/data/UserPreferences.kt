package com.astrorehber.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

private val Context.dataStore by preferencesDataStore(name = "astrorehber_prefs")

class UserPreferences(private val context: Context) {

    private val json = Json { ignoreUnknownKeys = true; encodeDefaults = true }

    private val keyProfile = stringPreferencesKey("user_profile")

    val profile: Flow<UserProfile?> = context.dataStore.data.map { prefs ->
        prefs[keyProfile]?.let { runCatching { json.decodeFromString<UserProfile>(it) }.getOrNull() }
    }

    suspend fun save(profile: UserProfile) {
        context.dataStore.edit { prefs ->
            prefs[keyProfile] = json.encodeToString(UserProfile.serializer(), profile)
        }
    }

    suspend fun clear() {
        context.dataStore.edit { it.remove(keyProfile) }
    }
}
