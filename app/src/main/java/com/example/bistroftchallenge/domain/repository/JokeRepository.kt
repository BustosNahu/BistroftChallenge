package com.example.bistroftchallenge.domain.repository

import com.example.bistroftchallenge.data.utils.network.ApiResponse
import com.example.bistroftchallenge.domain.model.Joke

interface JokeRepository {
    suspend fun getJoke(): ApiResponse<Joke>
}