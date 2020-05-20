package com.foolography.swindy.ui.weatherlist

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foolography.swindy.api.WeatherDataRepository
import com.foolography.swindy.data.WeatherResponse
import com.foolography.swindy.util.AppStorage
import javax.inject.Inject


class WeatherListViewModel @Inject constructor(
    private val weatherDataRepository: WeatherDataRepository,
    private val appStorage: AppStorage
) :
    ViewModel() {

    var scrollPosition = MutableLiveData<Int>().apply { value = 0 }
    var isShowProgress = MutableLiveData<Int>().apply { value = View.VISIBLE }
    var showEmptyState = MutableLiveData<Boolean>().apply { value = appStorage.isEmpty() }
    var storageWatcher =
        MutableLiveData<ArrayList<String>>().apply { value = appStorage.getAllCities() }
    val weatherList by lazy {
        weatherDataRepository.getAllWeatherDetails(
            prepareList()
        )
    }

    fun isListEmpty() = appStorage.isEmpty()
    fun loadWeather(): LiveData<WeatherResponse> {
        var preparedList: String = prepareList()
        return weatherDataRepository.getAllWeatherDetails(preparedList)
    }

    private fun prepareList(): String {
        return appStorage.getAllCities().joinToString(separator = ",")
    }

    fun deleteCity(id: Long) {
        appStorage.deleteCity(id.toString())
    }

}