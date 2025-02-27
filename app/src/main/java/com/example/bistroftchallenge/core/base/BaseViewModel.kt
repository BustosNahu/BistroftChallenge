package com.example.bistroftchallenge.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

abstract class BaseViewModel<State> : ViewModel() {
    protected val _state = MutableStateFlow(this.defaultState())


    open val state = _state
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(50000), this.defaultState())

    protected abstract fun defaultState(): State

}