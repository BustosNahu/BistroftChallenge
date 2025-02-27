package com.example.bistroftchallenge.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.bistroftchallenge.core.navigation.ScreenRoutes
import com.example.bistroftchallenge.presentation.compontents.BistroftButton
import com.example.bistroftchallenge.presentation.compontents.BistroftTextField

@Composable
fun WelcomeScreen(
    navController: NavController,
    viewModel: WelcomeViewModel = hiltViewModel()
) {
    WelcomeScreenChild(
        uiState = viewModel.state.collectAsStateWithLifecycle(),
        onEvent = viewModel::handleEvents,
        onContinue = {
            navController.navigate(ScreenRoutes.HomeScreen.route)
        }
    )

}

@Composable
fun WelcomeScreenChild(
    uiState: State<WelcomeUiState>,
    onEvent: (WelcomeEvents) -> Unit,
    onContinue: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(Modifier.fillMaxWidth()) {
            BistroftTextField(
                text = uiState.value.user?.name ?: "",
                label = "Name Here",
                onValueChange = { onEvent(WelcomeEvents.OnNameTextFieldChange(it)) },
                isNumberTextField = false
            )
            BistroftButton(
                onClick = {
                    if(!uiState.value.user?.name.isNullOrEmpty()){
                        onEvent(WelcomeEvents.OnCountinueClick)
                        onContinue()
                    }
                },
                text = "Continue"
            )
        }

    }

}