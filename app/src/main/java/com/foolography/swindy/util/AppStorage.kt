package com.foolography.swindy.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import java.util.*
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
    fun getAllCities(): ArrayList<String>
    fun deleteCity(id: String)
}

internal class AppStorageImpl @Inject constructor(
    context: Context
) : AppStorage {
    private val stringListType = object : TypeToken<List<String>>() {}.type

    var preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    override fun isEmpty(): Boolean {
        return getAllCities().isNullOrEmpty()
    }

    override fun addCity(id: String) {
        var list = getAllCities()
        list.add(id)
        saveJson(list)
    }

    override fun getAllCities(): ArrayList<String> {
        return Gson().fromJson(preferences.getString(CITIES_TAG, "[]"), stringListType)
    }

    override fun deleteCity(id: String) {
        var list = getAllCities()
        list.remove(id)
        saveJson(list)
    }

    private fun saveJson(list: ArrayList<String>) {
        preferences.edit().putString(CITIES_TAG, Gson().toJson(list.distinct())).apply()
    }

    companion object {
        private const val CITIES_TAG = "cities"
    }
}