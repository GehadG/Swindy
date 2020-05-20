package com.foolography.swindy.ui.addcity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.foolography.swindy.api.Resource
import com.foolography.swindy.api.WeatherDataRepository
import com.foolography.swindy.data.CityData
import com.foolography.swindy.util.AppStorage
import javax.inject.Inject


class AddCityViewModel @Inject constructor(
    private val weatherDataRepository: WeatherDataRepository,
    private val appStorage: AppStorage
) :
    ViewModel() {

    fun isValidCity(city: String): Boolean {
        return !city.isNullOrBlank()
    }

    fun validateCity(city: String): LiveData<Resource<CityData>> {
        return weatherDataRepository.getCityData(city)
    }

    fun saveCity(id: Long) {
        appStorage.addCity(id.toString())
    }

}