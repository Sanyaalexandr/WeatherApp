package com.example.city_list.internal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.city_list.api.City
import com.example.city_list.internal.data.model.CityDTO
import com.example.city_list.internal.data.repository.CitiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

internal sealed interface CitiesListScreenState {
    data object Loading : CitiesListScreenState
    data object Error : CitiesListScreenState
    data class Success(
        val cities: List<City> = emptyList(),
    ) : CitiesListScreenState
}

internal sealed interface CitiesListScreenEvent {
    data object Update : CitiesListScreenEvent
}

@HiltViewModel
internal class CitiesListViewModel @Inject constructor(
    private val citiesRepository: CitiesRepository
) : ViewModel() {
    private val _screenState = MutableStateFlow<CitiesListScreenState>(
        CitiesListScreenState.Loading
    )
    val screenState = _screenState.asStateFlow()

    fun onEvent(event: CitiesListScreenEvent) {
        when (event) {
            CitiesListScreenEvent.Update -> onUpdate()
        }
    }

    init {
        loadCities()
    }

    private fun loadCities() {
        _screenState.update {
            CitiesListScreenState.Loading
        }
        viewModelScope.launch {
            citiesRepository.getCities()
                .onFailure {
                    _screenState.update { CitiesListScreenState.Error }
                }
                .onSuccess { cities ->
                    if (cities.isEmpty()) {
                        _screenState.update { CitiesListScreenState.Error }
                    } else {
                        onSuccess(
                            cities = cities.mapToCities()
                        )
                    }
                }
        }
    }

    private fun onUpdate() {
        loadCities()
    }

    private fun onSuccess(cities: List<City>) {
        _screenState.update { currentState ->
            if (currentState is CitiesListScreenState.Success) {
                currentState.copy(
                    cities = cities,
                )
            } else {
                CitiesListScreenState.Success(
                    cities = cities,
                )
            }
        }
    }

    private fun List<CityDTO>.mapToCities(): List<City> = this
        .sortedBy { cityDTO -> cityDTO.city }
        .mapNotNull { cityDTO ->
            cityDTO.mapToCityOrNull()
        }

    private fun CityDTO.mapToCityOrNull(): City? {
        val cityId = id.toIntOrNull() ?: return null
        val latitude = latitude.toFloatOrNull() ?: return null
        val longitude = longitude.toFloatOrNull() ?: return null
        if (city.isBlank()) return null
        return City(
            id = cityId,
            latitude = latitude,
            longitude = longitude,
            name = city
        )
    }
}