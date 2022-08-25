package com.example.gpsmap

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.gpsmap.ui.HomeScreen
import com.google.android.gms.maps.MapView

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var granted by remember { mutableStateOf(false) }
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(), 
                onResult = { isGranted ->
                    granted = isGranted
                }
            )
            if(ContextCompat.checkSelfPermission(
                    this, 
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                granted = true
            }
            
            if(granted) {
                lifecycle.addObserver(viewModel)
                HomeScreen(viewModel)
            }
            else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "권한이 허용되지 않았습니다.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }) {
                        Text(text = "권한 요청")
                    }
                }
            }
        }
    }
}