package com.weather_app.weather_forecast.util.permission

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun showInContextUI(
    context: Context,
    message: String,
    launchPermissionRequest: () -> Unit
) {
    AlertDialog.Builder(context)
        .setTitle("Permission Required")
        .setMessage(message)
        .setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            // Request the permissions again after showing the rationale
            launchPermissionRequest()
        }
        .setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        .show()
}

fun checkForLocationPermission(context: Context, launchPermissionRequest: () -> Unit, activity: Activity) {
    when {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED -> {
            return
        }

        ActivityCompat.shouldShowRequestPermissionRationale(
            activity, Manifest.permission.ACCESS_FINE_LOCATION
        ) || ActivityCompat.shouldShowRequestPermissionRationale(
            activity, Manifest.permission.ACCESS_COARSE_LOCATION
        ) -> {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected, and what
            // features are disabled if it's declined. In this UI, include a
            // "cancel" or "no thanks" button that lets the user continue
            // using your app without granting the permission.
            showInContextUI(
                context = context,
                message = "Please grant location access to load weather forecast",
                launchPermissionRequest = {
                    launchPermissionRequest()
                }
            )
        }
        else -> {
            // If user has not given permission yet
            launchPermissionRequest()
        }
    }
}