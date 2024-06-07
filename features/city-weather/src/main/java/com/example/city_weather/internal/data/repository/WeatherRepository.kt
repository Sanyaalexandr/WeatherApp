package com.example.city_weather.internal.data.repository

import com.example.city_weather.internal.data.model.WeatherDTO
import com.example.city_weather.internal.data.service.WeatherService
import javax.inject.Inject

internal interface WeatherRepository {
    suspend fun getWeather(
        latitude: Float,
        longitude: Float,
    ): Result<WeatherDTO>
}

internal class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService,
): WeatherRepository {
    override suspend fun getWeather(latitude: Float, longitude: Float): Result<WeatherDTO> {
        return weatherService.getWeather(
            latitude = latitude,
            longitude = longitude
        )
    }
}