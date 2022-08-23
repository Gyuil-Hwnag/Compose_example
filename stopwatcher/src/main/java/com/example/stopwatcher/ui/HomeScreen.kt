package com.example.stopwatcher.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    second: Int,
    milli: Int,
    isRunning: Boolean,
    recordList: List<String>,
    onReset: () -> Unit,
    onPlay: (Boolean) -> Unit,
    onRecord: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "STOP WATCHER") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(text = "$second", fontSize = 96.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "$milli", fontSize = 24.sp)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                recordList.forEach { item ->
                    Text(text = item)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FloatingActionButton(
                    onClick = { onReset() },
                    backgroundColor = Color.Red)
                {
                    Image(painter = painterResource(
                        id = com.example.stopwatcher.R.drawable.ic_clear),
                        contentDescription = "clear")
                }

                FloatingActionButton(
                    onClick = { onPlay(isRunning) },
                    backgroundColor = Color.Green)
                {
                    Image(painter = painterResource(
                        id = if(isRunning) com.example.stopwatcher.R.drawable.ic_pause
                        else com.example.stopwatcher.R.drawable.ic_start),
                        contentDescription = "시작/정지")
                }

                Button(onClick = { onRecord() }) {
                    Text(text = "기록")
                }
            }
        }
    }
}