package com.example.sample1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ResultScreen(
    bmi: Double,
    toBack: () -> Unit
) {
    val text = when {
        bmi >= 25 -> "비만"
        bmi >= 23 -> "과제중"
        bmi >= 18.5 -> "정상"
        else -> "저체중"
    }

    val imageRes = when {
        bmi >= 25 -> com.example.sample1.R.drawable.ic_over_weight
        bmi >= 23 -> com.example.sample1.R.drawable.ic_normal_weight
        bmi >= 18.5 -> com.example.sample1.R.drawable.ic_good_weight
        else -> com.example.sample1.R.drawable.ic_over_weight
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "비만도 결과") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "ToBack",
                        modifier = Modifier.clickable {
                            toBack()
                        })
                }
            )

        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = text, fontSize = 30.sp)
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(96.dp),
                colorFilter = ColorFilter.tint(Color.Black)
            )
        }
    }
}