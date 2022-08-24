package com.example.gallery

import android.app.Application
import android.content.ContentUris
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _photoUri = mutableStateOf(emptyList<Uri>())
    val photoUri: State<List<Uri>> = _photoUri

    fun fetchPhotos() {
        val uriList = mutableListOf<Uri>()
        getApplication<Application>().contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC"
        )?.use { cusor ->
            val idIndex = cusor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

            while (cusor.moveToNext()) {
                val id = cusor.getLong(idIndex)
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                uriList.add(contentUri)
            }
        }

        _photoUri.value = uriList
    }
}