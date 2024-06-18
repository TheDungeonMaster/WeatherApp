package com.weather_app.weather_forecast.domain.data_classes

import android.health.connect.datatypes.units.Temperature

data class WeatherData(

    val currentWeatherData: CurrentWeatherData,
    // map{ Day - { Hour - WeatherData }}
    val hourlyWeatherData: Map<Int, Map<Int, HourlyWeatherData>> ,
    // list{ Day - WeatherData}
    val dailyWeatherData: List<DailyWeatherData>
)


data class CurrentWeatherData(
    val time: String,
    val temperature: Int,
    val humidity: Int,
    val apparentTemperature: Int,
    val precipitation: Int,
    val weatherCode: Int,
    val cloudCover: Int,
    val pressure: Int,
    val windSpeed: Int,
    val windGustsSpeed: Int
)

data class HourlyWeatherData (
    val time: String,
    val temperature: Int,
    val humidity: Int,
    val apparentTemperature: Int,
    val precipitation: Int,
    val weatherCode: Int,
    val cloudCover: Int,
    val windSpeed: Int,
)

data class DailyWeatherData (
    val time: String,
    val weatherCode: Int,
    val maxTemperature: Int,
    val minTemperature: Int,
    val sunrise: String,
    val sunset: String,
    val uvIndex: Int,
    val windSpeed: Int,
    val windGustsSpeed: Int,
    val windDirection: String,
)