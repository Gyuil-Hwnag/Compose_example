package com.example.compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Main()
            }
        }
    }

    @Preview
    @Composable
    fun Main() {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Greeting()
            Spacer(modifier = Modifier.height(8.dp))
            NameString("Hwang")
            BoxEx("Box Test")
            Row {
                ImageCard(Modifier
                    .fillMaxWidth(0.5f)
                    .padding(16.dp))
                ImageCard(Modifier
                    .fillMaxWidth(1f)
                    .padding(16.dp))
            }
//            ListOfColumn()
            ListOfLazyColumn()
        }
    }
}




