package com.example.sensor

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sensor.ui.HomeScreen

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        // 화면 종료X & 가로모드 고정
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        setContent {
            HomeScreen(
                x = viewModel.x.value,
                y = viewModel.y.value
            )
        }
    }
}

@Composable
@Preview
fun Preview() {
    HomeScreen(x = 30f, y = 30f)
}