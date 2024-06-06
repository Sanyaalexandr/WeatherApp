package com.example.weatherapp.data.repository.cities

import com.example.weatherapp.data.model.CityDTO
import com.example.weatherapp.data.service.CitiesService
import javax.inject.Inject

interface CitiesRepository {
    suspend fun getCities(): Result<List<CityDTO>>
}

class CitiesRepositoryImpl @Inject constructor(
    private val citiesService: CitiesService
): CitiesRepository {
    override suspend fun getCities(): Result<List<CityDTO>> =
        citiesService.getCities()
}