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

    @Test
    fun `Factorial de 10 debe ser 3628800`() {
        val result = factorialUseCase.calculateFactorial(10)
        assertEquals(BigInteger.valueOf(3_628_800), result)
    }

    @Test
    fun `Factorial de 20 debe ser correcto`() {
        val expected = BigInteger("2432902008176640000") // 20!
        val result = factorialUseCase.calculateFactorial(20)
        assertEquals(expected, result)
    }

    @Test
    fun `Factorial de número negativo debe devolver -1`() {
        val result = factorialUseCase.calculateFactorial(-5)
        assertEquals(BigInteger.valueOf(-1), result)
    }

    @Test
    fun `Factorial de 100 debe calcularse sin errores`() {
        val result = factorialUseCase.calculateFactorial(100)
        assert(result.toString().startsWith("933262154439441526816992388562667004"))
    }
}