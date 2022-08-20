package com.example.compose

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _data: MutableState<String> = mutableStateOf("")
    val data: State<String> = _data

    private val _liveData = MutableLiveData<String>()
    val liveData: LiveData<String> = _liveData

    fun changeData(value: String) {
        _data.value = value
    }
}