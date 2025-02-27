package com.example.bistroftchallenge.presentation.compontents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BistroftTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    supportingText: String = "",
    isNumberTextField: Boolean = true
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFECEFF7),
            unfocusedIndicatorColor = Color.LightGray,
            focusedIndicatorColor = Color(0xFF356C84),
            cursorColor = Color(0xFF0E4259),
            focusedLabelColor = Color(0xFF356C84),

            ),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .fillMaxWidth(),
        label = {
            Text(
                text = label,
                color = Color.Gray,
                fontSize = 16.sp
            )
        },
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    text = supportingText,
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isNumberTextField) KeyboardType.Number else KeyboardType.Text
        )

    )
}