package cd.wayupdotdev.ecodim.core.data_preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class PreferenceManager(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_prefs")

        val USER_ID = stringPreferencesKey("user_id")
        val USERNAME = stringPreferencesKey("username")
    }

    suspend fun saveUserId(uid: String) {
        context.dataStore.edit { it[USER_ID] = uid }
    }

    suspend fun saveUsername(name: String) {
        context.dataStore.edit { it[USERNAME] = name }
    }

    suspend fun getUserId(): String? {
        return context.dataStore.data.first()[USER_ID]
    }

    suspend fun getUsername(): String? {
        return context.dataStore.data.first()[USERNAME]
    }
}
