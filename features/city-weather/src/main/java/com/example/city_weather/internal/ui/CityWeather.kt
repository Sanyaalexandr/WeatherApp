package com.example.city_weather.internal.ui

import androidx.compose.runtime.Immutable

@Immutable
internal data class CityWeather(
    val city: String,
    val temp: Int,
)