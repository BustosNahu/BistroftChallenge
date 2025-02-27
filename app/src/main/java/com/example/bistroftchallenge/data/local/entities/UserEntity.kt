package com.example.bistroftchallenge.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bistroftchallenge.domain.model.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val name: String,
) {
    fun toDomain(): User {
        return User(
            name = this.name
        )
    }
}
