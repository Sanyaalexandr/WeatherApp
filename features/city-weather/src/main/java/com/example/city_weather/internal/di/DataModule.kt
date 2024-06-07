package com.example.city_weather.internal.di

import com.example.city_weather.internal.data.repository.WeatherRepository
import com.example.city_weather.internal.data.repository.WeatherRepositoryImpl
import com.example.city_weather.internal.data.service.WeatherService
import com.example.city_weather.internal.data.service.WeatherServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepository: WeatherRepositoryImpl
    ): WeatherRepository

    companion object {
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
}