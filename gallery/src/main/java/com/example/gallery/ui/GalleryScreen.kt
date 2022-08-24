package com.example.gallery.ui

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.ImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun GalleryScreen(photoUris: List<Uri>) {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            count = photoUris.size,
            modifier = Modifier
                .weight(1f)
                .padding(24.dp)
                .fillMaxSize()
        ) { pageIdx ->
            Card(
                modifier = Modifier.graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(pageIdx).absoluteValue

                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = photoUris[pageIdx]
                    ),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.fillMaxSize().align(Alignment.CenterHorizontally).padding(16.dp)
        )
    }
}

private fun lerp(start: Float, stop: Float, fraction: Float): Float =
    (1-fraction)*start + fraction*stop