package com.example.city_weather.ui.screencontent

import androidx.compose.runtime.Composable
import com.example.city_weather.ui.CityWeatherScreenEvent
import com.example.design.components.Error

@Composable
fun CityWeatherErrorScreenContent(
    onEvent: (CityWeatherScreenEvent) -> Unit,
) {
    Error(
        onUpdateClick = { onEvent.invoke(CityWeatherScreenEvent.Update) }
    )
}