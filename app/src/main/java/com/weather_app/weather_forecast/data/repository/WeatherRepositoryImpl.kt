package com.weather_app.weather_forecast.data.repository

import android.util.Log
import com.weather_app.weather_forecast.data.remote.WeatherAPI
import com.weather_app.weather_forecast.data.remote.WeatherDTO
import com.weather_app.weather_forecast.domain.data_classes.WeatherData
import com.weather_app.weather_forecast.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherAPI: WeatherAPI
): WeatherRepository {


    //TODO("change return type to weatherDATA")
    override suspend fun getWeatherData(latitude: Double, longitude: Double): WeatherDTO? {
        try {
            return weatherAPI.getWeatherData(latitude, longitude)

        } catch(e: Exception) {
            Log.d("TAG", "weather Data NOT loaded")
            e.printStackTrace()
            return null
        }
    }
}