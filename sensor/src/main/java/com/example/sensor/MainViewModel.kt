package com.example.sensor

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.getSystemService
import androidx.lifecycle.*

class MainViewModel(application: Application) : AndroidViewModel(application), LifecycleEventObserver, SensorEventListener {

    private val _x = mutableStateOf(0f)
    val x: State<Float> = _x

    private val _y = mutableStateOf(0f)
    val y: State<Float> = _y

    private val sensorManager by lazy {
        application.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    fun registerSensor() {
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    fun unregisterSensor() {
        sensorManager.unregisterListener(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_RESUME) {
            registerSensor()
        } else if (event == Lifecycle.Event.ON_PAUSE) {
            unregisterSensor()
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            _x.value = event.values[0]
            _y.value = event.values[1]
        }
    }

    override fun onAccuracyChanged(event: Sensor?, accuracy: Int) {

    }
}