package com.example.bistroftchallenge.data.repository

import com.example.bistroftchallenge.data.local.entities.LifecycleEventEntity
import com.example.bistroftchallenge.data.local.lifecycle.LifecycleEventDao
import com.example.bistroftchallenge.domain.model.LifecycleEvent
import com.example.bistroftchallenge.domain.repository.LifecycleEventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LifecycleEventRepositoryImpl @Inject constructor(
    private val dao: LifecycleEventDao
): LifecycleEventRepository{
    override suspend fun insertEvent(event: LifecycleEvent) {
        dao.insertEvent(LifecycleEventEntity.fromDomain(event))
    }

    override fun getAllEvents(): Flow<List<LifecycleEvent>> {
            return dao.getAllEvents().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun clearAllEvents() {
        dao.clearAllEvents()
    }

}