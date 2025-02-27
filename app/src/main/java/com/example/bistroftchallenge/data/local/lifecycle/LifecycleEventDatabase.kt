package com.example.bistroftchallenge.data.local.lifecycle

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bistroftchallenge.data.local.entities.LifecycleEventEntity

@Database(entities = [LifecycleEventEntity::class], version = 1, exportSchema = false)
abstract class LifecycleEventDatabase : RoomDatabase() {
    abstract fun lifecycleEventDao(): LifecycleEventDao

    companion object {
        @Volatile
        private var INSTANCE: LifecycleEventDatabase? = null

        fun getDatabase(context: Context): LifecycleEventDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LifecycleEventDatabase::class.java,
                    "lifecycle_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}