package com.foolography.swindy.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @field:SerializedName("cnt")
    val count: Integer,
    @field:SerializedName("list")
    val citiesList: List<CityData>
)