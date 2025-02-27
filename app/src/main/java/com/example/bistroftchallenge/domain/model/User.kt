package com.example.bistroftchallenge.domain.model

import com.example.bistroftchallenge.data.local.entities.UserEntity

data class User(
    val name: String,
) {
    fun toEntity(): UserEntity {
        return UserEntity(
            this.name
        )
    }
}
