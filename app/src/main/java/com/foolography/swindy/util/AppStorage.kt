package com.foolography.swindy.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Inject


@Module
class AppStorageModule {
    @Provides
    fun provideAppStorage(context: Context): AppStorage {
        return AppStorageImpl(context)
    }
}

interface AppStorage {
    fun isEmpty(): Boolean
    fun addCity(id: String)
    fun getAllCities(): HashSet<String>?
}

internal class AppStorageImpl @Inject constructor(
    context: Context
) : AppStorage {

    var preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    override fun isEmpty(): Boolean {
        return getAllCities().isNullOrEmpty()
    }

    override fun addCity(id: String) {
        val set: MutableSet<String> = HashSet()
        getAllCities()?.let { set.addAll(it) }
        set.add(id)
        preferences.edit().putStringSet(CITIES_TAG, set).apply()
    }

    override fun getAllCities(): HashSet<String>? {
        return preferences.getStringSet(CITIES_TAG, null) as HashSet<String>?
    }

    companion object {
        private const val CITIES_TAG = "cities"
    }
}