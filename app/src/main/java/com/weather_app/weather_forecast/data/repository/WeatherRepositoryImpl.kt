package com.weather_app.weather_forecast.data.repository

import com.weather_app.weather_forecast.domain.data_classes.WeatherData
import com.weather_app.weather_forecast.domain.repository.WeatherRepository

class WeatherRepositoryImpl : WeatherRepository {

    override suspend fun getWeatherData(latitude: Int, longitude: Int): WeatherData {
        TODO("call API using latitude and longitude")
    }
}