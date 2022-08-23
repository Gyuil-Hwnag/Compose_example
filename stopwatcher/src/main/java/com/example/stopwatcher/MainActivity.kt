package com.example.stopwatcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stopwatcher.ui.HomeScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<MainViewModel>()

            val sec = viewModel.sec.value
            val milli = viewModel.milli.value
            val isRunning = viewModel.state.value
            val recordList = viewModel.recordList.value

            HomeScreen(second = sec,
                milli = milli,
                isRunning = isRunning,
                recordList = recordList,
                onReset = { viewModel.reset() },
                onPlay = { state ->
                    if(state) viewModel.pause()
                    else viewModel.start()
                },
                onRecord = { viewModel.recordTime() }
            )
        }
    }
}