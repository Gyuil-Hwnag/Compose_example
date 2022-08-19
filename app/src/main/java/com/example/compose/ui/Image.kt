package com.example.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.R

@Composable
fun ImageCard(
    modifier: Modifier
) {
    /*
    화면 전환등에 대한 보존이 필요할 경우 rememberSaveable
    아닐 경우 remember
    */
    var isFavorite by rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .background(Color.Black)
        ) {
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Image",
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(onClick = {
                    isFavorite = !isFavorite
                }) {
                    Icon(
                        imageVector = if(isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "좋아요",
                        tint = Color.White
                    )
                }
            }
        }
    }
}