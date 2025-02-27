package com.example.bistroftchallenge.data.repository

import com.example.bistroftchallenge.data.remote.joke.JokeDataSource
import com.example.bistroftchallenge.data.utils.network.ApiResponse
import com.example.bistroftchallenge.domain.model.Joke
import com.example.bistroftchallenge.domain.repository.JokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    private val jokeDataSource: JokeDataSource
) : JokeRepository {
    override suspend fun getJoke(): ApiResponse<Joke> {
        return withContext(Dispatchers.IO) {
            jokeDataSource.getJoke().map { it.toDomain() }
        }
    }
}
