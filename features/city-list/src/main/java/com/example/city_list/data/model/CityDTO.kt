package com.example.city_list.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CityDTO(
    val id: String,
    val city: String,
    val latitude: String,
    val longitude: String,
)
