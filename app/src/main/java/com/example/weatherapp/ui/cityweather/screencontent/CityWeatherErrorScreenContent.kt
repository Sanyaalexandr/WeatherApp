package com.example.weatherapp.ui.cityweather.screencontent

import androidx.compose.runtime.Composable
import com.example.weatherapp.ui.citieslist.CitiesListScreenEvent
import com.example.weatherapp.ui.cityweather.CityWeatherScreenEvent
import com.example.weatherapp.ui.components.Error

@Composable
fun CityWeatherErrorScreenContent(
    onEvent: (CityWeatherScreenEvent) -> Unit,
) {
    Error(
        onUpdateClick = { onEvent.invoke(CityWeatherScreenEvent.Update) }
    )
}