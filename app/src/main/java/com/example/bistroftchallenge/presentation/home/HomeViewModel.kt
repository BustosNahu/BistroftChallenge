package com.example.bistroftchallenge.presentation.home

import android.util.Log
import androidx.lifecycle.SAVED_STATE_REGISTRY_OWNER_KEY
import androidx.lifecycle.viewModelScope
import com.example.bistroftchallenge.core.base.BaseViewModel
import com.example.bistroftchallenge.data.utils.network.ApiResponse
import com.example.bistroftchallenge.domain.UseCases.FactorialUseCase
import com.example.bistroftchallenge.domain.repository.JokeRepository
import com.example.bistroftchallenge.domain.repository.LifecycleEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val lifecycleEventRepository: LifecycleEventRepository,
    private val jokeRepository: JokeRepository,
    private val factorialUseCase: FactorialUseCase
) : BaseViewModel<HomeUiState>() {

    init {
        getLifecycleEvents()
    }

    private fun getLifecycleEvents() {
        viewModelScope.launch {
            lifecycleEventRepository.getAllEvents().collect { eventList ->
                _state.update {
                    it.copy(
                        lifecycleEvent = eventList
                    )
                }
            }
        }
    }


    fun handleEvent(event: HomeEvents) {
        when (event) {
            HomeEvents.OnCalculateClick -> calculateNumberFactorial()
            HomeEvents.OnConsumeDataClick -> getJoke()
            is HomeEvents.OnNumberTextFieldChange -> updateNumberTextField(event.number)
            HomeEvents.OnClearLifecycle -> clearRoomDb()
        }
    }

    private fun clearRoomDb() {
        viewModelScope.launch {
            lifecycleEventRepository.clearAllEvents()
        }
    }

    private fun calculateNumberFactorial() {
        _state.update {
            it.copy(
                isCalculateFactorialLoading = true
            )
        }
        val number = state.value.textFieldNumber.toIntOrNull()
        if (number != null && number >= 0) {
            _state.update {
                it.copy(
                    factorialNumberResult = "The result is: ${
                        factorialUseCase.calculateFactorial(number)
                    }",
                    isCalculateFactorialLoading = false
                )
            }
            Log.d("FACTORIAL", "calculateNumberFactorial: ${state.value.factorialNumberResult}")
        } else {
            _state.update {
                it.copy(
                    isError = true,
                    isCalculateFactorialLoading = false
                )
            }
        }
    }

    private fun updateNumberTextField(number: String) {
        _state.update {
            it.copy(
                textFieldNumber = number
            )
        }
    }

    private fun getJoke() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isJokeLoading = true
                )
            }
            when (val response = jokeRepository.getJoke()) {
                is ApiResponse.Error -> {
                    _state.update {
                        it.copy(
                            isJokeLoading = false,
                            isError = true
                        )
                    }
                }

                is ApiResponse.Success -> {
                    _state.update {
                        it.copy(
                            joke = response.data,
                            jokeMessage = response.data.setup + " " + response.data.delivery,
                            isJokeLoading = false
                        )
                    }
                    getJokeOrDelivery()
                }
            }
        }
    }

    private fun getJokeOrDelivery() {
        if ((state.value.joke?.setup != null) && (state.value.joke?.delivery != null)) {
            _state.update {
                it.copy(
                    jokeMessage = state.value.joke!!.setup + " " + state.value.joke!!.delivery,
                )
            }
        } else {
            _state.update {
                it.copy(
                    jokeMessage = state.value.joke?.joke!!
                )
            }
        }
    }

    override fun defaultState(): HomeUiState {
        return HomeUiState()
    }
}