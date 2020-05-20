package com.foolography.swindy.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.foolography.swindy.data.CityData
import com.foolography.swindy.data.WeatherResponse
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDataRepository @Inject constructor(private val service: WeatherService) {

    fun getAllWeatherDetails(cities: String?): LiveData<WeatherResponse> {
        return liveData(Dispatchers.IO) {
            try {
                val weatherList = cities?.let { service.getAllWeatherDetails(it) }
                weatherList?.let { emit(it) }
            } catch (er: Exception) {
                er.printStackTrace()
            }
        }
    }

    fun getCityData(city: String): LiveData<Resource<CityData>> {
        return liveData(Dispatchers.IO) {
            try {
                val weatherList = service.getCityData(city)
                emit(Resource(Status.SUCCESS, weatherList, null))
            } catch (er: Exception) {
                emit(Resource(Status.ERROR, null, "City Not Found"))
            }
        }
    }
}