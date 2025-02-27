package com.example.bistroftchallenge.presentation.home

sealed interface HomeEvents {

    data object OnCalculateClick : HomeEvents
    data object OnConsumeDataClick: HomeEvents
    data object OnClearLifecycle: HomeEvents
    data class OnNumberTextFieldChange(val number: String): HomeEvents
}