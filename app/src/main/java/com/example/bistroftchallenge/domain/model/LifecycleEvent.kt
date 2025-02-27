package com.example.bistroftchallenge.domain.model

data class LifecycleEvent(
    val id: Int = 0,
    val event: String,
    val timestamp: Long,
    val activityName: String
)
