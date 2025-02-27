package com.example.bistroftchallenge.presentation.main


sealed interface WelcomeEvents {
    data class OnNameTextFieldChange(val name: String): WelcomeEvents
    data object OnCountinueClick: WelcomeEvents
}