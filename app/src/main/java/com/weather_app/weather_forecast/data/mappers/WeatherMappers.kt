package com.weather_app.weather_forecast.data.mappers

import com.weather_app.weather_forecast.data.remote.CurrentWeatherDTO
import com.weather_app.weather_forecast.data.remote.DailyWeatherDTO
import com.weather_app.weather_forecast.data.remote.HourlyWeatherDTO
import com.weather_app.weather_forecast.data.remote.WeatherDTO
import com.weather_app.weather_forecast.domain.data_classes.CurrentWeatherData
import com.weather_app.weather_forecast.domain.data_classes.DailyWeatherData
import com.weather_app.weather_forecast.domain.data_classes.HourlyWeatherData
import com.weather_app.weather_forecast.domain.data_classes.WeatherData


fun CurrentWeatherDTO.toCurrentWeatherData(): CurrentWeatherData {
    return CurrentWeatherData(
        time = this.time,
        temperature = this.temperature,
        humidity = this.relativeHumidity,
        apparentTemperature = this.apparentTemperature,
        precipitation = this.precipitation,
        weatherCode = this.weatherCode,
        cloudCover = this.cloudCover,
        pressure = this.surfacePressure,
        windSpeed = this.windSpeed,
        windGustsSpeed = this.windGusts
    )
}

fun DailyWeatherDTO.toDailyWeatherData(): List<DailyWeatherData> {
    return List(time.size) { index ->
        DailyWeatherData(
            time = this.time[index],
            weatherCode = this.weatherCode[index],
            maxTemperature = this.maxTemperature[index],
            minTemperature = this.minTemperature[index],
            sunrise = this.sunrise[index],
            sunset = this.sunset[index],
            uvIndex = this.uvIndex[index],
            windSpeed = this.windSpeed[index],
            windGustsSpeed = this.windGustsSpeed[index],
            windDirection = this.windDirection[index]
        )
    }
}

fun HourlyWeatherDTO.toHourlyWeatherData() : Map<Int, List<HourlyWeatherData>> {
    return List(time.size) {index ->
        IndexedHourlyWeatherData(
            index = index,
            data = HourlyWeatherData(
                time = this.time[index],
                temperature = this.temperature[index],
                humidity = this.relativeHumidity[index],
                apparentTemperature = this.apparentTemperature[index],
                precipitation = this.precipitation[index],
                weatherCode = this.weatherCode[index],
                cloudCover = this.cloudCover[index],
                windSpeed = this.windSpeed[index]
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues { it ->
        it.value.map{ it.data }
    }
}

private data class IndexedHourlyWeatherData(
    val index: Int,
    val data: HourlyWeatherData
)

fun WeatherDTO.toWeatherData() : WeatherData {
    return WeatherData(
        currentWeatherData = currentWeatherDTO.toCurrentWeatherData(),
        hourlyWeatherData = hourlyWeatherDTO.toHourlyWeatherData(),
        dailyWeatherData = dailyWeatherDTO.toDailyWeatherData()
    )
}