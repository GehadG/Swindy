package com.foolography.swindy.ui.weatherlist

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.foolography.swindy.api.WeatherDataRepository
import com.foolography.swindy.data.WeatherResponse
import javax.inject.Inject


class WeatherListViewModel @Inject constructor(private val weatherDataRepository: WeatherDataRepository) :
    ViewModel() {

    var scrollPosition = MutableLiveData<Int>().apply { value = 0 }
    var isShowProgress = MutableLiveData<Int>().apply { value = View.VISIBLE }

    val weatherList by lazy { weatherDataRepository.getAllWeatherDetails() }

    fun loadWeather(): LiveData<WeatherResponse> {
        return weatherDataRepository.getAllWeatherDetails()
    }

}