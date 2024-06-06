package com.example.weatherapp.ui.citieslist

import com.example.weatherapp.data.model.CityDTO

data class City(
    val id: Int,
    val name: String,
    val latitude: Float,
    val longitude: Float,
)
