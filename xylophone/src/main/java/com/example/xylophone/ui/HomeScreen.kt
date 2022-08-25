package com.example.xylophone.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xylophone.MainViewModel

@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val keys = listOf(
        Pair("도", Color.Red),
        Pair("레", Color.Yellow),
        Pair("미", Color.Black),
        Pair("파", Color.Cyan),
        Pair("솔", Color.Blue),
        Pair("라", Color.DarkGray),
        Pair("시", Color.Magenta),
        Pair("도", Color.Red)
    )

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        keys.forEachIndexed { index, key ->
            val padding = (index+2)*8
            KeyBoard(
                modifier = Modifier
                    .padding(top = padding.dp, bottom = padding.dp)
                    .background(color = key.second)
                    .clickable { viewModel.playSound(index) }
                    .fillMaxHeight()
                    .width(50.dp),
                text = key.first
            )
        }
    }
}

@Composable
fun KeyBoard(
    modifier: Modifier,
    text: String
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = text,
            style = TextStyle(color = Color.White, fontSize = 20.sp),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}