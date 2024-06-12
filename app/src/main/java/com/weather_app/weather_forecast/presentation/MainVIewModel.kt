package com.weather_app.weather_forecast.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.weather_app.weather_forecast.data.location.DefaultLocationTracker


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @OptIn(ExperimentalCoroutinesApi::class)
@Inject constructor(
    private val locationClient: DefaultLocationTracker
): ViewModel() {

    val curLocation = MutableStateFlow("")

    init {
        getLastKnownLocation()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getLastKnownLocation() {
        viewModelScope.launch {
            val location = locationClient.getCurrentLocation()
            if (location != null) {
                curLocation.value = "${location.latitude}, ${location.longitude}"
            }else {
                curLocation.value = "NO location"
            }
        }

    }


}