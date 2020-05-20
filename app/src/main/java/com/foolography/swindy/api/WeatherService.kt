package com.foolography.swindy.api

import com.foolography.swindy.data.WeatherResponse
import retrofit2.http.GET

interface WeatherService {
    companion object {
        const val ENDPOINT = "http://api.openweathermap.org/data/2.5/"
    }

    @GET("group?id=524901,703448,2643743&units=metric&APPID=f4c88235415efd04c515a4a113fb7ea7")
    suspend fun getAllWeatherDetails(): WeatherResponse

}