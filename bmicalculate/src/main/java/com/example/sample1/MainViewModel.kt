package com.example.sample1

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.math.pow

class MainViewModel : ViewModel() {
    private val _data = mutableStateOf(0.0)
    val data = _data

    fun setData(weight: Double, height: Double) {
        _data.value = weight/(height/100).pow(2.0)
    }
}