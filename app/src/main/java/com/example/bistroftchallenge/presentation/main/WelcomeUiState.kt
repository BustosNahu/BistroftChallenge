package com.example.bistroftchallenge.presentation.main

import com.example.bistroftchallenge.domain.model.User

data class WelcomeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val user: User? = null
)
