package com.weather_app.weather_forecast.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.weather_app.weather_forecast.domain.location.LocationTracker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

@ExperimentalCoroutinesApi
class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
): LocationTracker {

    override suspend fun getCurrentLocation(): Location? {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if(!hasAccessCoarseLocationPermission && !hasAccessFineLocationPermission && !isGpsEnabled) {
            Log.d("LOCATION_TRACKER", "no access granted")
            return null
        }

        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful && result != null) {
                        Log.d("LOCATION_TRACKER", "Last known location: ${result.latitude}, ${result.longitude}")
                        cont.resume(result)
                    } else {
                        Log.d("LOCATION_TRACKER", "Last known location not found")
                        cont.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener { location ->
                    if (location != null) {
                        Log.d("LOCATION_TRACKER", "New location fetched: ${location.latitude}, ${location.longitude}")
                        cont.resume(location)
                    } else {
                        Log.d("LOCATION_TRACKER", "New location fetch failed")
                        cont.resume(null)
                    }
                }
                addOnFailureListener {
                    Log.e("LOCATION_TRACKER", "Location request failed", it)
                    cont.resume(null)
                }
                addOnCanceledListener {
                    Log.d("LOCATION_TRACKER", "Location request cancelled")
                    cont.cancel()
                }
            }
        }
    }
}