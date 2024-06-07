package com.example.city_weather.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.city_weather.internal.ui.CityWeatherScreenEvent
import com.example.city_weather.internal.ui.CityWeatherScreenState
import com.example.city_weather.internal.ui.CityWeatherViewModel
import com.example.city_weather.internal.ui.screencontent.CityWeatherErrorScreenContent
import com.example.city_weather.internal.ui.screencontent.CityWeatherLoadingScreenContent
import com.example.city_weather.internal.ui.screencontent.CityWeatherSuccessScreenContent

@Composable
fun CityWeatherScreen(
    cityName: String,
    latitude: Float,
    longitude: Float,
) {
    val weatherViewModel = hiltViewModel<CityWeatherViewModel>()
    LaunchedEffect(
        key1 = latitude,
        key2 = longitude,
    ) {
        weatherViewModel.onEvent(
            CityWeatherScreenEvent.SelectCityWeather(
                cityName = cityName,
                latitude = latitude,
                longitude = longitude,
            )
        )
    }
    val state = weatherViewModel.screenState.collectAsState()
    when(val stateValue = state.value) {
        is CityWeatherScreenState.Success -> CityWeatherSuccessScreenContent(
            weather = stateValue.cityWeather,
            onEvent = weatherViewModel::onEvent
        )
        CityWeatherScreenState.Error -> CityWeatherErrorScreenContent(
            onEvent = weatherViewModel::onEvent
        )
        CityWeatherScreenState.Loading -> CityWeatherLoadingScreenContent()
    }
}