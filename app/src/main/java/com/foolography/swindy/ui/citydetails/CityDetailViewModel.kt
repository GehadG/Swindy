package com.foolography.swindy.ui.citydetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.foolography.swindy.api.WeatherDataRepository
import com.foolography.swindy.data.CityData
import com.foolography.swindy.util.AppStorage
import javax.inject.Inject


class CityDetailViewModel @Inject constructor(
    private val weatherDataRepository: WeatherDataRepository,
    private val appStorage: AppStorage
) :
    ViewModel() {

    fun isValidCity(city: String): Boolean {
        return !city.isNullOrBlank()
    }

    fun validateCity(city: String): LiveData<CityData> {
        return weatherDataRepository.getCityData(city)
    }

    fun saveCity(id: Long) {
        appStorage.addCity(id.toString())
    }

}