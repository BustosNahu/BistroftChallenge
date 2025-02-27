package com.example.bistroftchallenge.presentation.compontents

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bistroftchallenge.R

@Composable
fun BistroftCircularProgressBarGif(
    modifier: Modifier = Modifier,
    tintColor: Color = Color.White
) {
    Box(
        modifier = modifier
    ) {
        val infiniteTransition = rememberInfiniteTransition(label = "")
        val angle by infiniteTransition.animateFloat(
            initialValue = 0F,
            targetValue = 360F,
            animationSpec = infiniteRepeatable(
                animation = tween(700, easing = LinearEasing)
            ), label = ""
        )
        Icon(painter = painterResource(R.drawable.pic_progress_bar),
            contentDescription = "",
            Modifier
                .align(Alignment.Center)
                .size(20.dp)
                .graphicsLayer {
                    rotationZ = angle
                },
            tint = tintColor
        )

    }
}