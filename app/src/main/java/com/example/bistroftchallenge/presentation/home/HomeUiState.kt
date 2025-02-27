package com.example.bistroftchallenge.presentation.home

import com.example.bistroftchallenge.domain.model.Joke
import com.example.bistroftchallenge.domain.model.LifecycleEvent

data class HomeUiState (
    val isError: Boolean = false,
    val isLoading: Boolean = true,
    val isJokeLoading: Boolean = false,
    val isCalculateFactorialLoading: Boolean = false,
    val joke: Joke? = null,
    val jokeMessage: String = "",
    val textFieldNumber: String = "",
    val factorialNumberResult: String = "",
    val lifecycleEvent: List<LifecycleEvent> = emptyList()
)