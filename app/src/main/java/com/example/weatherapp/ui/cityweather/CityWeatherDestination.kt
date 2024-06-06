package com.example.weatherapp.ui.cityweather

import kotlinx.serialization.Serializable

@Serializable
data class CityWeatherDestination(
    val cityName: String,
    val latitude: Float,
    val longitude: Float,
)
