package com.foolography.swindy.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityData(
    @field:SerializedName("sys")
    val country: CountryData,
    @field:SerializedName("weather")
    val weather: List<WeatherData>,
    @field:SerializedName("main")
    val main: MainData,
    @field:SerializedName("wind")
    val wind: WindData,
    @field:SerializedName("clouds")
    val clouds: CloudsData,
    @field:SerializedName("dt")
    val dayTime: Long,
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("name")
    val name: String
) : Parcelable

@Parcelize
data class CountryData(
    @field:SerializedName("type")
    val type: Double,
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("country")
    val country: String,
    @field:SerializedName("sunrise")
    val sunrise: Long,
    @field:SerializedName("sunset")
    val sunset: Long
) : Parcelable

@Parcelize
data class WeatherData(
    @field:SerializedName("main")
    val main: String,
    @field:SerializedName("id")
    val id: Long,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("icon")
    val icon: String
) : Parcelable

@Parcelize
data class MainData(
    @field:SerializedName("temp")
    val currentTemp: Double,
    @field:SerializedName("pressure")
    val pressure: Double,
    @field:SerializedName("humidity")
    val humidity: Double,
    @field:SerializedName("temp_min")
    val minimumTemp: Double,
    @field:SerializedName("temp_max")
    val maximumTemp: Double
) : Parcelable

@Parcelize
data class WindData(
    @field:SerializedName("speed")
    val speed: Double,
    @field:SerializedName("deg")
    val degree: Double
) : Parcelable

@Parcelize
data class CloudsData(
    @field:SerializedName("all")
    val all: Double
) : Parcelable