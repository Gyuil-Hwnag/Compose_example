package com.example.compose.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListOfColumn() {
    val scrollState: ScrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(8.dp)
            .verticalScroll(scrollState)
    ) {
        for(i in 1..50) {
            Text(text = "test $i")
        }
    }
}

@Composable
fun ListOfLazyColumn() {
    LazyColumn(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(text = "Header")
        }
        items(50) { index ->
            Text(text = "Lazy Test $index")
        }
        item {
            Text(text = "Footer")
        }
    }
}