package com.example.sensor.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun HomeScreen(x: Float, y: Float) {
    val yy = x*20
    val xx = x*20

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val centerX = size.width/2
        val centerY = size.height/2

        // 바깥원
        drawCircle(
            Color.Black,
            radius = 100f,
            center = Offset(centerX, centerY),
            style = Stroke()
        )
        // 가속도
        drawCircle(
            Color.Green,
            radius = 100f,
            center = Offset(xx+centerX, yy+centerY)
        )

        // 중점
        drawLine(
            color = Color.Black,
            start = Offset(centerX, centerY-20),
            end = Offset(centerX, centerY+20)
        )
        drawLine(
            color = Color.Black,
            start = Offset(centerX-20, centerY),
            end = Offset(centerX+20, centerY)
        )
    }
}