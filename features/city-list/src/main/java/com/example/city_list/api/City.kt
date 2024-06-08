package com.example.city_list.api

import androidx.compose.runtime.Immutable

@Immutable
data class City(
    val id: Int,
    val name: String,
    val latitude: Float,
    val longitude: Float,
)
