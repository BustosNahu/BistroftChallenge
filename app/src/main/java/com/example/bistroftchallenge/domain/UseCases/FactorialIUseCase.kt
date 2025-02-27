package com.example.bistroftchallenge.domain.UseCases

import javax.inject.Inject

class FactorialUseCase @Inject constructor() {
    fun calculateFactorial(number: Int): Long {
        if (number < 0) return -1
        return if (number == 0 || number == 1) 1 else (1..number).fold(1L) { acc, i -> acc * i }
    }
}