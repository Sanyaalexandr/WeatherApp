package com.example.city_weather.internal.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class WeatherDTO(
    val name: String,
    val main: WeatherMain,
    val coord: WeatherCoord,
)

@Serializable
internal data class WeatherMain(
    val temp: Float
)

@Serializable
internal data class WeatherCoord(
    val lon: Float,
    val lat: Float,
)