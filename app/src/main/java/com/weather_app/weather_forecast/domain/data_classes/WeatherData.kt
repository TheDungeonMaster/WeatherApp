package com.weather_app.weather_forecast.domain.data_classes

import android.health.connect.datatypes.units.Temperature

data class WeatherData(

    val currentWeatherData: CurrentWeatherData,
    val hourlyWeatherData: HourlyWeatherData
)


data class CurrentWeatherData(
    val temperature: Int
)

data class HourlyWeatherData (
    val temperature: Int
)