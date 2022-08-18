package com.example.compose

import android.os.Bundle
import android.print.PrintAttributes
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greeting()
        }
    }

    @Preview
    @Composable
    fun Greeting() {
        Column {
            Text(text = "Compose Sample1")
            Text("Hello, World1", style = TextStyle(color = Color.Red))
            Row {
                Text(text = "Compose Sample2")
                Text("Hello, World2", style = TextStyle(color = Color.Red))
            }
        }

    }
}
