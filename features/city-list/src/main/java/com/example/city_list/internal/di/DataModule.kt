package com.example.city_list.internal.di

import com.example.city_list.internal.data.repository.CitiesRepository
import com.example.city_list.internal.data.repository.CitiesRepositoryImpl
import com.example.city_list.internal.data.service.CitiesService
import com.example.city_list.internal.data.service.CitiesServiceImpl
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
    abstract fun bindCitiesRepository(
        citiesRepositoryImpl: CitiesRepositoryImpl
    ): CitiesRepository

    companion object {
        @Provides
        @Singleton
        fun provideCitiesService(
            httpClient: HttpClient
        ) : CitiesService {
            return CitiesServiceImpl(
                client = httpClient
            )
        }
    }
}