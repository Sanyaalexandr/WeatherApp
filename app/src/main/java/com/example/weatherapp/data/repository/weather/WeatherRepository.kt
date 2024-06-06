package com.example.weatherapp.data.repository.weather

import com.example.weatherapp.data.model.WeatherDTO
import com.example.weatherapp.data.service.WeatherService
import javax.inject.Inject

interface WeatherRepository {
    suspend fun getWeather(
        latitude: Float,
        longitude: Float,
    ): Result<WeatherDTO>
}

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
): WeatherRepository {
    override suspend fun getWeather(latitude: Float, longitude: Float): Result<WeatherDTO> {
        return weatherService.getWeather(
            latitude = latitude,
            longitude = longitude
        )
    }
}