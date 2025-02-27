package com.example.bistroftchallenge.domain.UseCases

import java.math.BigInteger
import javax.inject.Inject

class FactorialUseCase @Inject constructor() {
    fun calculateFactorial(n: Int): BigInteger {
        if (n < 0) retfacurn BigInteger.valueOf(-1)

        var result = BigInteger.ONE
        var i = n

        while (i > 1) {
            result *= BigInteger.valueOf(i.toLong())
            i--
        }

        return result
    }
}