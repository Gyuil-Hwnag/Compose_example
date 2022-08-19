package com.example.compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun Greeting() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(text = "Compose Sample1")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Hello, World1", style = TextStyle(color = Color.Red))
        Spacer(modifier = Modifier.width(24.dp))
        Row(
            modifier = Modifier
                .background(color = Color.Blue)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Compose Sample2")
            Text("Hello, World2", style = TextStyle(color = Color.Red))
        }
    }
}

@Composable
fun NameString(name: String) {
    Text(
        text = name,
        modifier = Modifier
            .padding(16.dp)
            .background(Color.Cyan)
    )
}

@Composable
fun BoxEx(text: String) {
    Box(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column {
            Text(text = text)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = text+"11111"
                )
            }
        }
    }
}