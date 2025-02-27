package com.example.bistroftchallenge.domain.repository

import com.example.bistroftchallenge.domain.model.LifecycleEvent
import kotlinx.coroutines.flow.Flow

interface LifecycleEventRepository {

    suspend fun insertEvent(event: LifecycleEvent)

    fun getAllEvents(): Flow<List<LifecycleEvent>>

    suspend fun clearAllEvents()
}