package com.example.stopwatcher

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.timer

class MainViewModel : ViewModel() {
    private var time = 0
    private var timerTask: Timer? = null

    private val _sec = mutableStateOf(0)
    val sec: State<Int> = _sec

    private val _milli = mutableStateOf(0)
    val milli: State<Int> = _milli

    private val _state = mutableStateOf(false)
    val state: State<Boolean> = _state

    private var record = 1

    private val _recordList = mutableStateOf(mutableListOf<String>())
    val recordList = _recordList

    fun start() {
        _state.value = true

        timerTask = timer(period = 10) {
            time+=1
            _sec.value = time / 100
            _milli.value = time % 100
        }
    }

    fun pause() {
        _state.value = false
        timerTask?.cancel()
    }

    fun reset() {
        timerTask?.cancel()
        time = 0
        _state.value = false
        _sec.value = 0
        _milli.value = 0

        _recordList.value.clear()
        record = 1
    }

    fun recordTime() {
        _recordList.value.add(0, "$record.번째 : ${sec.value}. : ${milli.value}.")
        record+=1
    }
}