package com.example.bistroftchallenge.data.remote.joke

import com.example.bistroftchallenge.data.remote.joke.dto.JokeDto
import com.example.bistroftchallenge.data.utils.network.ApiResponse

interface JokeDataSource {
    suspend fun getJoke(): ApiResponse<JokeDto>
}