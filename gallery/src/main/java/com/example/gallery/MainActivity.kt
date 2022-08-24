package com.example.gallery

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gallery.ui.GalleryScreen
import com.example.gallery.ui.PermissionRequestScreen
import kotlinx.coroutines.launch
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<MainViewModel>()
            var granted by remember { mutableStateOf(false) }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()) { isGranted ->
                granted = isGranted
            }

            if(ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED) {
                granted = true
            }

            if(granted) {
                viewModel.fetchPhotos()
                GalleryScreen(photoUris = viewModel.photoUri.value)
            }
            else {
                PermissionRequestScreen {
                    launcher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
    }
}