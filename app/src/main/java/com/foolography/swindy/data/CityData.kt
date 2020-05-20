package com.foolography.swindy.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

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
    val name: String,
    @field:SerializedName("cod")
    val code: Int
) : Parcelable {

    fun getParsedDate(): String {
        return try {
            val sdf = SimpleDateFormat("E, dd.MM.yyyy")
            val netDate = Date(dayTime * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }

    }

    fun getLogo(): String {
        return "http://openweathermap.org/img/wn/${weather[0].icon}.png"
    }

    fun getDetailedLogo(): String {
        return "http://openweathermap.org/img/wn/${weather[0].icon}@4x.png"
    }
}

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
    @field:SerializedName("feels_like")
    val feelsLike: Double,
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