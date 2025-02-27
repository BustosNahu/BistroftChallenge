package com.example.bistroftchallenge.data.utils.network

import android.util.Log
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.http.HttpStatusCode

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String) : ApiResponse<Nothing>()

    fun <R> map(transform: (T) -> R): ApiResponse<R> {
        return when (this) {
            is Success -> Success(transform(data))
            is Error -> this
        }
    }
}


fun handleApiException(e: Exception): ApiResponse.Error {
    val errorMessage = when (e) {
        is RedirectResponseException -> "La solicitud fue redirigida a otra ubicación."
        is ClientRequestException -> {
            when (e.response.status) {
                HttpStatusCode.Unauthorized -> "Credenciales inválidas. Verifica tu usuario y contraseña."
                HttpStatusCode.Forbidden -> "Acceso denegado. No tienes permiso para acceder a este recurso."
                HttpStatusCode.NotFound -> "Recurso no encontrado en el servidor."
                else -> "Error al procesar la solicitud."
            }
        }
        is ServerResponseException -> {
            when (e.response.status) {
                HttpStatusCode.InternalServerError -> "Error interno del servidor."
                HttpStatusCode.BadGateway -> "Error en la comunicación con el servidor de origen."
                HttpStatusCode.ServiceUnavailable -> "El servicio no está disponible en este momento. Inténtalo más tarde."
                HttpStatusCode.GatewayTimeout -> "El servidor no respondió a tiempo."
                else -> "Error en el servidor."
            }
        }
        else -> "Error de conexión. Verifica tu conexión a internet."
    }

    val responseStatusCode = (e as? ServerResponseException)?.response?.status
        ?: (e as? ClientRequestException)?.response?.status
        ?: if (e.message?.contains("Gateway Time-out") == true) HttpStatusCode.GatewayTimeout else HttpStatusCode.InternalServerError

    Log.e("ApiResponse", "Error en la solicitud: ${e.localizedMessage ?: "Sin detalles"}", e)
    Log.e("ApiResponse", "Código de respuesta del servidor: $responseStatusCode")

    return ApiResponse.Error(
        message = errorMessage,
    )
}
