package com.example.weatherapp.di

import com.example.weatherapp.data.service.CitiesService
import com.example.weatherapp.data.service.CitiesServiceImpl
import com.example.weatherapp.data.service.WeatherService
import com.example.weatherapp.data.service.WeatherServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            })
        }
    }

    @Provides
    @Singleton
    fun provideCitiesService(
        httpClient: HttpClient
    ) : CitiesService {
        return CitiesServiceImpl(
            client = httpClient
        )
    }

    @Provides
    @Singleton
    fun provideWeatherService(
        httpClient: HttpClient
    ): WeatherService {
        return WeatherServiceImpl(
            client = httpClient
        )
    }
}