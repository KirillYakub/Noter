package com.kiras.noter.data.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import com.kiras.noter.domain.accounts.model.AuthInfo
import com.kiras.noter.domain.accounts.repository.AuthActiveSessionStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

class AuthActiveAuthActiveSessionStorageImpl(
    private val dataStore: DataStore<Preferences>,
): AuthActiveSessionStorage {

    private companion object {
        private val ACTIVE_ACCOUNT_KEY = stringPreferencesKey("active_account_id")
    }

    override suspend fun get(): AuthInfo? {
        return withContext(Dispatchers.IO) {
            val json = dataStore.data.map { prefs -> prefs[ACTIVE_ACCOUNT_KEY] }.firstOrNull()
            json?.let { Json.decodeFromString<AuthInfoSerializable>(it).toAuthInfo() }
        }
    }

    override suspend fun set(authInfo: AuthInfo?) {
        withContext(Dispatchers.IO) {
            dataStore.edit { prefs ->
                if (authInfo == null) {
                    prefs.remove(ACTIVE_ACCOUNT_KEY)
                } else {
                    val json = Json.encodeToString(authInfo.toAuthInfoSerializable())
                    prefs[ACTIVE_ACCOUNT_KEY] = json
                }
            }
        }
    }
}