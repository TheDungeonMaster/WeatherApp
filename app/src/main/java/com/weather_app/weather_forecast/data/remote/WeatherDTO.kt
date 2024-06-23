package com.weather_app.weather_forecast.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WeatherDTO(

    @SerialName("current") val currentWeatherDTO: CurrentWeatherDTO,
    @SerialName("hourly") val hourlyWeatherDTO: HourlyWeatherDTO,
    @SerialName("daily") val dailyWeatherDTO: DailyWeatherDTO
)


@Serializable
data class HourlyWeatherDTO(
    val time: List<String>,
    @SerialName("temperature_2m") val temperature: List <Double>,
    @SerialName("relative_humidity_2m") val relativeHumidity: List <Double>,
    @SerialName("apparent_temperature") val apparentTemperature: List <Double>,
    @SerialName("precipitation_probability") val precipitationProbability: List <Double>,
    @SerialName("precipitation") val precipitation: List<Double>,
    @SerialName("weather_code") val weatherCode:  List<Double>,
    @SerialName("cloud_cover") val cloudCover: List<Double>,
    @SerialName("wind_speed_10m") val windSpeed: List<Double>,
    @SerialName("wind_gusts_10m") val windGusts: List<Double>,

)

@Serializable
data class CurrentWeatherDTO(
    @SerialName("time") val time: String,
    @SerialName("temperature_2m") val temperature: Double,
    @SerialName("relative_humidity_2m") val relativeHumidity: Double,
    @SerialName("apparent_temperature") val apparentTemperature: Double,
    @SerialName("precipitation") val precipitation: Double,
    @SerialName("weather_code") val weatherCode:  Double,
    @SerialName("cloud_cover") val cloudCover: Double,
    @SerialName("surface_pressure") val surfacePressure: Double,
    @SerialName("wind_speed_10m") val windSpeed: Double,
    @SerialName("wind_gusts_10m") val windGusts: Double,

)

@Serializable
data class DailyWeatherDTO (
    @SerialName("time") val time: List<String>,
    @SerialName("weather_code") val weatherCode: List<Double>,
    @SerialName("temperature_2m_max") val maxTemperature: List<Double>,
    @SerialName("temperature_2m_min") val minTemperature: List<Double>,
    @SerialName("sunrise") val sunrise: List<String>,
    @SerialName("sunset") val sunset: List<String>,
    @SerialName("uv_index_max") val uvIndex: List<Double>,
    @SerialName("wind_speed_10m_max") val windSpeed: List<Double>,
    @SerialName("wind_gusts_10m_max") val windGustsSpeed: List<Double>,
    @SerialName("wind_direction_10m_dominant") val windDirection: List<String>,
)