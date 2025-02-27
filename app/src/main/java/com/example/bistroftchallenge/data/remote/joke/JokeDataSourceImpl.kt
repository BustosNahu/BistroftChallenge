package com.example.bistroftchallenge.data.remote.joke

import android.util.Log
import com.example.bistroftchallenge.data.remote.joke.dto.JokeDto
import com.example.bistroftchallenge.data.utils.network.ApiResponse
import com.example.bistroftchallenge.data.utils.network.HttpRoutes.PROGRAMMING_JOKE_URL
import com.example.bistroftchallenge.data.utils.network.handleApiException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import javax.inject.Inject
import kotlin.math.log

class JokeDataSourceImpl @Inject constructor(
    private val client: HttpClient
): JokeDataSource {
    override suspend fun getJoke(): ApiResponse<JokeDto> {
        return try {
            val response = client.get(PROGRAMMING_JOKE_URL){
                contentType(ContentType.Application.Json)
                parameter("lang", "es")
            }
            return if(response.status == HttpStatusCode.OK){
                val responseBody = response.body<JokeDto>()
                ApiResponse.Success(responseBody)
            }else{
                ApiResponse.Error("Error getting joke")
            }
        }catch (e: Exception){
            handleApiException(e)
        }
    }
}