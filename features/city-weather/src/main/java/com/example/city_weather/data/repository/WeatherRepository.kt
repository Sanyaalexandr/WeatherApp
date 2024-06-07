package com.example.city_weather.data.repository

import com.example.city_weather.data.model.WeatherDTO
import com.example.city_weather.data.service.WeatherService
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