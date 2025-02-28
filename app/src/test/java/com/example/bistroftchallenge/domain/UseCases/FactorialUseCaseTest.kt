package com.example.bistroftchallenge.domain.UseCases

import org.junit.Assert.*
import org.junit.Test
import java.math.BigInteger

class FactorialUseCaseTest{
    private val factorialUseCase = FactorialUseCase()

    @Test
    fun `Factorial de 0 debe ser 1`() {
        val result = factorialUseCase.calculateFactorial(0)
        assertEquals(BigInteger.ONE, result)
    }

    @Test
    fun `Factorial de 1 debe ser 1`() {
        val result = factorialUseCase.calculateFactorial(1)
        assertEquals(BigInteger.ONE, result)
    }

    @Test
    fun `Factorial de 5 debe ser 120`() {
        val result = factorialUseCase.calculateFactorial(5)
        assertEquals(BigInteger.valueOf(120), result)
    }
}