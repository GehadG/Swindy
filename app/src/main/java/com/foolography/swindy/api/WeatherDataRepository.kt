package com.foolography.swindy.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.foolography.swindy.data.WeatherResponse
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDataRepository @Inject constructor(private val service: WeatherService) {

    fun getAllWeatherDetails(): LiveData<WeatherResponse> {
        return liveData(Dispatchers.IO) {
            try {
                val newsList = service.getAllWeatherDetails()
                emit(newsList)
            } catch (er: Exception) {
                er.printStackTrace()
            }
        }
    }
}