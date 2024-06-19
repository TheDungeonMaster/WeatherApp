package com.weather_app.weather_forecast.domain.repository

import com.weather_app.weather_forecast.data.remote.WeatherDTO
import com.weather_app.weather_forecast.domain.data_classes.WeatherData

interface WeatherRepository {

    suspend fun getWeatherData(latitude: Double,  longitude: Double) : WeatherDTO?

}