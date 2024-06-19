package com.weather_app.weather_forecast.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.weather_app.weather_forecast.data.location.DefaultLocationTracker
import com.weather_app.weather_forecast.domain.repository.WeatherRepository


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @OptIn(ExperimentalCoroutinesApi::class)
@Inject constructor(
    private val locationClient: DefaultLocationTracker,
    private val weatherRepository: WeatherRepository

): ViewModel() {

    val curLocation = MutableStateFlow(Location())
    val temp = MutableStateFlow(0)
    val wind = MutableStateFlow(0)

    init {
        getLastKnownLocation()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getLastKnownLocation() {
        viewModelScope.launch {
            val location = locationClient.getCurrentLocation()
            if (location != null) {
                curLocation.update {
                    Location(
                        latitude = location.latitude,
                        longitude = location.longitude
                    )
                }
                Log.d("TAG","LOCATION FETCHED", )

            }else {
                curLocation.value.latitude = null
                curLocation.value.longitude = null
                Log.d("TAG","LOCATION NOT FETCHED", )
            }

            getWeatherData()
        }

    }

    private fun getWeatherData() {
        viewModelScope.launch {
            var lat = curLocation.value.latitude
            var lon = curLocation.value.longitude

            if (lat != null && lon != null) {
                val data = weatherRepository.getWeatherData(lat, lon)
                if (data != null) {
                    temp.value = data.currentWeatherDTO.temperature.toInt()
                    wind.value = data.currentWeatherDTO.windSpeed.toInt()
                    Log.d("TAG", "CURRENT_ ${temp.value}")
                }
            } else {
                Log.d("VM", "could not get location")
            }
        }
    }


}

data class Location(
        var latitude: Double? = null,
        var longitude: Double? = null
)