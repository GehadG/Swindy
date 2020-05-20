package com.foolography.swindy.api

import com.foolography.swindy.data.CityData
import com.foolography.swindy.data.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    companion object {
        const val ENDPOINT = "http://api.openweathermap.org/data/2.5/"
    }

    @GET("group?units=metric&APPID=f4c88235415efd04c515a4a113fb7ea7")
    suspend fun getAllWeatherDetails(@Query("id") cities: String): WeatherResponse

    @GET("weather?units=metric&APPID=f4c88235415efd04c515a4a113fb7ea7")
    suspend fun getCityData(@Query("q") city: String): CityData

}