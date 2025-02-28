package com.example.bistroftchallenge.domain.repository

import com.example.bistroftchallenge.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun setUserData(user: User)
    suspend fun getUser(): Flow<User?>
    suspend fun deleteUser()
}