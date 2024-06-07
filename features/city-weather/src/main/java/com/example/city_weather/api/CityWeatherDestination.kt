package com.example.city_weather.api

import kotlinx.serialization.Serializable

@Serializable
data class CityWeatherDestination(
    val cityName: String,
    val latitude: Float,
    val longitude: Float,
)
