package com.example.weatherapp.data.service

import com.example.weatherapp.data.model.WeatherDTO
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface WeatherService {
    suspend fun getWeather(
        latitude: Float,
        longitude: Float,
    ): Result<WeatherDTO>
}

class WeatherServiceImpl(
    private val client: HttpClient,
): WeatherService {
    override suspend fun getWeather(
        latitude: Float,
        longitude: Float,
    ): Result<WeatherDTO> {
        return try {
            val weather = client.get<WeatherDTO>(WEATHER_URL) {
                parameter(LAT_PARAM, latitude)
                parameter(LON_PARAM, longitude)
                parameter(APP_ID_PARAM, API_KEY)
                parameter(UNITS_PARAM, DEFAULT_TEMP_METRIC)
            }
            Result.success(weather)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

private const val API_KEY = "5a6696464d8f29246a3f25f162445a95"
private const val DEFAULT_TEMP_METRIC = "metric"

private const val WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather"
private const val LAT_PARAM = "lat"
private const val LON_PARAM = "lon"
private const val UNITS_PARAM = "units"
private const val APP_ID_PARAM = "appid"