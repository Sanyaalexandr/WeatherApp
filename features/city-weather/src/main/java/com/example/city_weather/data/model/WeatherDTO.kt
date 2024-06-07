package com.example.city_weather.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDTO(
    val name: String,
    val main: WeatherMain,
    val coord: WeatherCoord,
)

@Serializable
data class WeatherMain(
    val temp: Float
)

@Serializable
data class WeatherCoord(
    val lon: Float,
    val lat: Float,
)