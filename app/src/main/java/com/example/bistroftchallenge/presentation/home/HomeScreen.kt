package com.example.bistroftchallenge.presentation.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.bistroftchallenge.R
import com.example.bistroftchallenge.presentation.compontents.BistroftButton
import com.example.bistroftchallenge.presentation.compontents.BistroftTextField
import com.example.bistroftchallenge.presentation.compontents.LifecycleCard
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreenChild(
        uiState = viewModel.state.collectAsStateWithLifecycle(),
        onEvent = viewModel::handleEvent,
    )
}


@Composable
private fun HomeScreenChild(
    uiState: State<HomeUiState>,
    onEvent: (HomeEvents) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.value.joke, uiState.value.factorialNumberResult) {
        if (uiState.value.joke != null) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = uiState.value.jokeMessage,
                    duration = SnackbarDuration.Long
                )
            }
        } else if (uiState.value.factorialNumberResult.isNotEmpty()) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = uiState.value.factorialNumberResult,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .padding(30.dp)
                .fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(90.dp)
                ) {
                    Text(
                        text = "¡Hola Nahuel!",
                        modifier = Modifier.padding(top = 40.dp),
                        fontSize = 28.sp
                    )
                    Column {
                        BistroftTextField(
                            text = uiState.value.textFieldNumber,
                            label = "Number to calculate",
                            onValueChange = { onEvent(HomeEvents.OnNumberTextFieldChange(it)) },
                        )
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            BistroftButton(
                                modifier = Modifier.width(130.dp),
                                onClick = {
                                    onEvent(HomeEvents.OnCalculateClick)
                                },
                                text = "Calculate"
                            )
                            BistroftButton(
                                modifier = Modifier.width(130.dp),
                                onClick = {
                                    onEvent(HomeEvents.OnConsumeDataClick)
                                },
                                text = "Get Joke",
                                isLoading = uiState.value.isJokeLoading
                            )
                        }
                    }

                    Column(
                        Modifier.fillMaxWidth()
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Lifecycle Record",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
                            )
                            IconButton(onClick = {
                                onEvent(HomeEvents.OnClearLifecycle)
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable._0518457671581426692),
                                    contentDescription = "clear lifecycle",
                                )
                            }
                        }
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            itemsIndexed(uiState.value.lifecycleEvent) { index, event ->
                                if (index == 0) {
                                    Spacer(modifier = Modifier.height(20.dp))
                                }
                                LifecycleCard(lifecycleEvent = event)
                            }
                        }
                    }
                }
            }
        }

    }

}

