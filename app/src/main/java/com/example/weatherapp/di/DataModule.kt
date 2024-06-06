package com.example.weatherapp.di

import com.example.weatherapp.data.repository.cities.CitiesRepository
import com.example.weatherapp.data.repository.cities.CitiesRepositoryImpl
import com.example.weatherapp.data.repository.weather.WeatherRepository
import com.example.weatherapp.data.repository.weather.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun bindCitiesRepository(
        citiesRepositoryImpl: CitiesRepositoryImpl
    ): CitiesRepository

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(
        weatherRepository: WeatherRepositoryImpl
    ): WeatherRepository
}