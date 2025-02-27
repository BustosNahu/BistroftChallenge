package com.example.bistroftchallenge.core.di

import android.content.Context
import androidx.room.Room
import com.example.bistroftchallenge.core.lifecycle.AppLifecycleObserver
import com.example.bistroftchallenge.data.local.lifecycle.LifecycleEventDao
import com.example.bistroftchallenge.data.local.lifecycle.LifecycleEventDatabase
import com.example.bistroftchallenge.data.remote.joke.JokeDataSource
import com.example.bistroftchallenge.data.remote.joke.JokeDataSourceImpl
import com.example.bistroftchallenge.data.repository.JokeRepositoryImpl
import com.example.bistroftchallenge.data.repository.LifecycleEventRepositoryImpl
import com.example.bistroftchallenge.domain.UseCases.FactorialUseCase
import com.example.bistroftchallenge.domain.repository.JokeRepository
import com.example.bistroftchallenge.domain.repository.LifecycleEventRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): LifecycleEventDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            LifecycleEventDatabase::class.java,
            "lifecycle_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideLifecycleEventDao(database: LifecycleEventDatabase): LifecycleEventDao {
        return database.lifecycleEventDao()
    }

    @Singleton
    @Provides
    fun provideLifecycleEventRepository(dao: LifecycleEventDao): LifecycleEventRepository {
        return LifecycleEventRepositoryImpl(dao)
    }

    @Singleton
    @Provides
    fun provideAppLifecycleObserver(lifecycleEventRepository: LifecycleEventRepository): AppLifecycleObserver {
        return AppLifecycleObserver(lifecycleEventRepository)
    }

    @Singleton
    @Provides
    fun provideJokeDataSource(client: HttpClient): JokeDataSource = JokeDataSourceImpl(client)

    @Singleton
    @Provides
    fun provideJokeRepository(jokeDataSource: JokeDataSource): JokeRepository =
        JokeRepositoryImpl(jokeDataSource)

    @Provides
    @Singleton
    fun provideFactorialUseCase(): FactorialUseCase = FactorialUseCase()

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        val client = HttpClient(CIO) {
            install(HttpCache)
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })

            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
                filter { request ->
                    request.url.host.contains("ktor.io")

                }
                sanitizeHeader { header -> header == HttpHeaders.Authorization }
            }
        }
        return client
    }
}
