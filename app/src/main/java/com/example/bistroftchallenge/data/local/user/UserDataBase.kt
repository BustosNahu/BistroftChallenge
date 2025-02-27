package com.example.bistroftchallenge.data.local.user

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bistroftchallenge.data.local.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}