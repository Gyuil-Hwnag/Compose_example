package com.example.xylophone

import android.app.Application
import android.media.SoundPool
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val soundPool = SoundPool.Builder().setMaxStreams(8).build()
    // sound 객체
    private val sounds = listOf(
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8
//        soundPool.load(application.applicationContext, com.example.xylophone.R)
//        soundPool.load(application.applicationContext, com.example.xylophone.R)
//        soundPool.load(application.applicationContext, com.example.xylophone.R)
//        soundPool.load(application.applicationContext, com.example.xylophone.R)
//        soundPool.load(application.applicationContext, com.example.xylophone.R)
//        soundPool.load(application.applicationContext, com.example.xylophone.R)
//        soundPool.load(application.applicationContext, com.example.xylophone.R)
//        soundPool.load(application.applicationContext, com.example.xylophone.R)
//        soundPool.load(application.applicationContext, com.example.xylophone.R)
    )

    fun playSound(index: Int) {
        soundPool.play(sounds[index], 1f, 1f, 0, 0, 1f)
    }

    override fun onCleared() {
        super.onCleared()
        soundPool.release()
    }
}