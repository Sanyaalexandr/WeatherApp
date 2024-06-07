package com.example.city_list.data.repository

import com.example.city_list.data.model.CityDTO
import com.example.city_list.data.service.CitiesService
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