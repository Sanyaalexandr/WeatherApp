package com.example.weatherapp.ui.cityweather

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.ui.cityweather.screencontent.CityWeatherErrorScreenContent
import com.example.weatherapp.ui.cityweather.screencontent.CityWeatherLoadingScreenContent
import com.example.weatherapp.ui.cityweather.screencontent.CityWeatherSuccessScreenContent

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
        weatherViewModel.onEvent(CityWeatherScreenEvent.SelectCityWeather(
            cityName = cityName,
            latitude = latitude,
            longitude = longitude,
        ))
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