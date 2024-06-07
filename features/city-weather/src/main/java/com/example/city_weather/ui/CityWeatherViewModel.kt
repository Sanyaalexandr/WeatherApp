package com.example.city_weather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.city_weather.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.round

sealed interface CityWeatherScreenState {
    data object Loading: CityWeatherScreenState
    data object Error: CityWeatherScreenState
    data class Success(
        val cityWeather: CityWeather,
    ): CityWeatherScreenState
}

sealed interface CityWeatherScreenEvent {
    data object Update: CityWeatherScreenEvent
    data class SelectCityWeather(
        val cityName: String,
        val latitude: Float,
        val longitude: Float,
    ): CityWeatherScreenEvent
}

@HiltViewModel
class CityWeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
): ViewModel() {
    private val _screenState = MutableStateFlow<CityWeatherScreenState>(
        CityWeatherScreenState.Loading
    )
    val screenState = _screenState.asStateFlow()

    fun onEvent(event: CityWeatherScreenEvent) {
        when(event) {
            CityWeatherScreenEvent.Update -> onUpdate()
            is CityWeatherScreenEvent.SelectCityWeather -> onWeatherSelected(
                cityName = event.cityName,
                latitude = event.latitude,
                longitude = event.longitude,
            )
        }
    }

    private fun onUpdate() {
        (_screenState.value as? CityWeatherScreenState.Success)
            ?.cityWeather
            ?.let { cityWeather ->
                loadWeather(
                    cityName = cityWeather.city,
                    latitude = cityWeather.latitude,
                    longitude = cityWeather.longitude,
                )
            }
    }

    private fun onWeatherSelected(
        cityName: String,
        latitude: Float,
        longitude: Float,
    ) {
        loadWeather(
            cityName = cityName,
            latitude = latitude,
            longitude = longitude,
        )
    }

    private fun loadWeather(
        cityName: String,
        latitude: Float,
        longitude: Float,
    ) {
        _screenState.update {
            CityWeatherScreenState.Loading
        }
        viewModelScope.launch {
            weatherRepository.getWeather(
                latitude = latitude,
                longitude = longitude,
            )
            .onFailure {
                _screenState.update { CityWeatherScreenState.Error }
            }
            .onSuccess { weatherDTO ->
                onSuccess(
                    cityWeather = CityWeather(
                        city = cityName,
                        temp = round(weatherDTO.main.temp).toInt(),
                        latitude = weatherDTO.coord.lat,
                        longitude = weatherDTO.coord.lon,
                    )
                )
            }
        }
    }

    private fun onSuccess(
        cityWeather: CityWeather
    ) {
        _screenState.update { currentState ->
            if (currentState is CityWeatherScreenState.Success) {
                currentState.copy(
                    cityWeather = cityWeather,
                )
            } else {
                CityWeatherScreenState.Success(
                    cityWeather = cityWeather,
                )
            }
        }
    }
}