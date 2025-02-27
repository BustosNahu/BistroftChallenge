package com.example.bistroftchallenge.data.repository

import com.example.bistroftchallenge.data.local.user.UserDao
import com.example.bistroftchallenge.domain.model.User
import com.example.bistroftchallenge.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao
) : UserRepository {
    override suspend fun setUserData(user: User) {
        dao.setUserData(user.toEntity())
    }

    override suspend fun getUser(): Flow<User> {
        return dao.getUser().map { it!!.toDomain() }
    }

    override suspend fun deleteUser() {
        dao.deleteUser()
    }
}