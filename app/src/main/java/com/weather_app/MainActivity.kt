package com.weather_app

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.weather_app.ui.theme.Weather_AppTheme
import com.weather_app.weather_forecast.presentation.MainViewModel
import com.weather_app.weather_forecast.util.permission.checkForLocationPermission
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.asStateFlow


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationPermissionRequest: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val context = this
        val mainViewModel: MainViewModel by viewModels()
        locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ) {
                mainViewModel.getLastKnownLocation()
            } else {
                // Handle permission denial here
                Toast.makeText(context, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
        checkForLocationPermission(
            context = context,
            launchPermissionRequest = {
                locationPermissionRequest.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            },
            activity = this
        )
        enableEdgeToEdge()
        setContent {
            Weather_AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val location = mainViewModel.curLocation.collectAsState()
                    val temp = mainViewModel.temp.collectAsState()
                    val wind = mainViewModel.wind.collectAsState()
                    Greeting(
                        name = "${location.value.latitude.toString()} and ${location.value.longitude.toString()}",
                        temp = temp.value.toString(),
                        wind = wind.value.toString(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


}

@Composable
fun Greeting(name: String, temp: String, wind: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!" +
                "\n TEMP = $temp" +
                "\n WIND = $wind",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Weather_AppTheme {
//        Greeting("Android")
//    }
//}



