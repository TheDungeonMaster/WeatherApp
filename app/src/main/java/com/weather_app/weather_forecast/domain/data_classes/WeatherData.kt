package com.weather_app.weather_forecast.domain.data_classes


data class WeatherData(

    val currentWeatherData: CurrentWeatherData,
    // map{ Day - { Hour - WeatherData }}
    val hourlyWeatherData: Map<Int, List<HourlyWeatherData>> ,
    val dailyWeatherData: List<DailyWeatherData>
)


data class CurrentWeatherData(
    val time: String,
    val temperature: Double,
    val humidity: Double,
    val apparentTemperature: Double,
    val precipitation: Double,
    val weatherCode: Double,
    val cloudCover: Double,
    val pressure: Double,
    val windSpeed: Double,
    val windGustsSpeed: Double
)

data class HourlyWeatherData (
    val time: String,
    val temperature: Double,
    val humidity: Double,
    val apparentTemperature: Double,
    val precipitation: Double,
    val weatherCode: Double,
    val cloudCover: Double,
    val windSpeed: Double,
)

data class DailyWeatherData (
    val time: String,
    val weatherCode: Double,
    val maxTemperature: Double,
    val minTemperature: Double,
    val sunrise: String,
    val sunset: String,
    val uvIndex: Double,
    val windSpeed: Double,
    val windGustsSpeed: Double,
    val windDirection: Int,
)