package com.weather_app.weather_forecast.data.repository

import android.util.Log
import com.weather_app.weather_forecast.data.mappers.toWeatherData
import com.weather_app.weather_forecast.data.remote.WeatherAPI
import com.weather_app.weather_forecast.data.remote.WeatherDTO
import com.weather_app.weather_forecast.domain.data_classes.CurrentWeatherData
import com.weather_app.weather_forecast.domain.data_classes.WeatherData
import com.weather_app.weather_forecast.domain.repository.WeatherRepository
import com.weather_app.weather_forecast.util.resource.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherAPI: WeatherAPI
): WeatherRepository {


    //TODO("change return type to weatherDATA")
    override suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherData> {
        try {
            return Resource.Success(data = weatherAPI.getWeatherData(latitude, longitude).toWeatherData())
        } catch(e: Exception) {
            Log.d("REPOSITORY", "WeatherData NOT loaded")
            e.printStackTrace()
            return Resource.Error("Error Retrieving Data")
        }
    }
}