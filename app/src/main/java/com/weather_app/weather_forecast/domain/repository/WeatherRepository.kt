package com.weather_app.weather_forecast.domain.repository

import com.weather_app.weather_forecast.data.remote.WeatherDTO
import com.weather_app.weather_forecast.domain.data_classes.WeatherData
import com.weather_app.weather_forecast.util.resource.Resource

interface WeatherRepository {

    suspend fun getWeatherData(latitude: Double,  longitude: Double) : Resource<WeatherData>

}