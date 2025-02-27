package com.example.bistroftchallenge.presentation.compontents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BistroftButton(
    modifier: Modifier = Modifier,
    text: String = "Confirm",
    onClick: () -> Unit,
    contentColor: Color = Color(0xFF0E4259),
    textColor: Color = White,
    borderColor: Color? = null,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    cornersShape: RoundedCornerShape = RoundedCornerShape(20.dp),
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp),
        onClick = {
            if (enabled) {
                onClick()
            }
        },
        color = contentColor,
        shape = cornersShape,
        border = if (borderColor != null) {
            BorderStroke(color = borderColor, width = 1.dp)
        } else {
            null
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isLoading) {
                BistroftCircularProgressBarGif()
            }else{
                Text(
                    text = text,
                    fontSize = 13.sp,
                    color = textColor,
                )
            }
        }


    }


}