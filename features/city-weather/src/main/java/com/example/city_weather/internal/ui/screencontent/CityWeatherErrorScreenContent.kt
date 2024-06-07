package com.example.city_weather.internal.ui.screencontent

import androidx.compose.runtime.Composable
import com.example.city_weather.internal.ui.CityWeatherScreenEvent
import com.example.design.components.Error

@Composable
internal fun CityWeatherErrorScreenContent(
    onEvent: (CityWeatherScreenEvent) -> Unit,
) {
    Error(
        onUpdateClick = {
            onEvent.invoke(CityWeatherScreenEvent.Update)
        }
    )
}