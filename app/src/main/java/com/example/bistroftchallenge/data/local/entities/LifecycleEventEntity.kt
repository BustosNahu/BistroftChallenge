package com.example.bistroftchallenge.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bistroftchallenge.domain.model.LifecycleEvent

@Entity(tableName = "lifecycle_events")
data class LifecycleEventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val event: String,
    val timestamp: Long,
    val activityName: String
){
    fun toDomain(): LifecycleEvent {
        return LifecycleEvent(
            id = this.id,
            event = this.event,
            timestamp = this.timestamp,
            activityName = this.activityName
        )
    }

    companion object {
        fun fromDomain(domain: LifecycleEvent): LifecycleEventEntity {
            return LifecycleEventEntity(
                id = domain.id,
                event = domain.event,
                timestamp = domain.timestamp,
                activityName = domain.activityName
            )
        }
    }
}

