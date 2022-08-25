package com.example.gpsmap

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Color
import android.location.Location
import android.os.Looper
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions

class MainViewModel(application: Application) : AndroidViewModel(application), LifecycleEventObserver {
    private val fusedLocationProviderClient = FusedLocationProviderClient(application.applicationContext)
    private val locationRequest: LocationRequest
    private val locationCallback: MyLocationCallBack

    private val _state = mutableStateOf(
        MapState(null, PolylineOptions().width(5f).color(Color.BLUE))
    )
    val state: State<MapState> = _state


    init {
        locationCallback = MyLocationCallBack()
        locationRequest = LocationRequest.create()
        // 10초 주기로 업데이트
        locationRequest.interval = 10000
        // 최소 5초 단위 미만으로는 안함
        locationRequest.fastestInterval = 5000
    }

    inner class MyLocationCallBack: LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)

            val location = locationResult.lastLocation
            val polylineOptions = state.value.polylineOptions

            location?.let {
                _state.value = state.value.copy(
                    location = location,
                    polylineOptions = polylineOptions.add(LatLng(it.latitude, it.longitude))
                )
            }

        }
    }

    @SuppressLint("MissingPermission")
    private fun addLocationListener() {
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun removeLocationListener() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_RESUME) {
            addLocationListener()
        } else if (event == Lifecycle.Event.ON_PAUSE) {
            removeLocationListener()
        }
    }
}

data class MapState(
    val location: Location?,
    val polylineOptions: PolylineOptions
)