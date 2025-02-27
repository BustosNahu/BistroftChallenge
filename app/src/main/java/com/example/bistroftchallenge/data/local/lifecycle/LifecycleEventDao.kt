package com.example.bistroftchallenge.data.local.lifecycle

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bistroftchallenge.data.local.entities.LifecycleEventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LifecycleEventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: LifecycleEventEntity)

    @Query("SELECT * FROM lifecycle_events ORDER BY timestamp DESC")
    fun getAllEvents(): Flow<List<LifecycleEventEntity>>

    @Query("DELETE FROM lifecycle_events")
    suspend fun clearAllEvents()
}