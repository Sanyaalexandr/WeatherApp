package com.example.city_weather.di

import com.example.city_weather.data.repository.WeatherRepository
import com.example.city_weather.data.repository.WeatherRepositoryImpl
import com.example.city_weather.data.service.WeatherService
import com.example.city_weather.data.service.WeatherServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
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