package com.example.bistroftchallenge.presentation.main

import androidx.lifecycle.viewModelScope
import com.example.bistroftchallenge.core.base.BaseViewModel
import com.example.bistroftchallenge.domain.model.User
import com.example.bistroftchallenge.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel<WelcomeUiState>() {

    init {
        setupData()
    }

    private fun setupData() {
        viewModelScope.launch {
            userRepository.deleteUser()
        }
    }

    fun handleEvents(welcomeEvents: WelcomeEvents) {
        when (welcomeEvents) {
            WelcomeEvents.OnCountinueClick -> saveUserNameToLocal()
            is WelcomeEvents.OnNameTextFieldChange -> updateUserName(welcomeEvents.name)
        }
    }

    private fun saveUserNameToLocal() {
        viewModelScope.launch {
            state.value.user?.let { userRepository.setUserData(it) }
        }
    }

    private fun updateUserName(name: String) {
        _state.update {
            it.copy(
                user = User(name)
            )
        }
    }

    override fun defaultState(): WelcomeUiState {
        return WelcomeUiState()
    }
}