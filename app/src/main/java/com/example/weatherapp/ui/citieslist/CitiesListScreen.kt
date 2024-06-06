package com.example.weatherapp.ui.citieslist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.ui.citieslist.screencontent.CitiesListErrorScreenContent
import com.example.weatherapp.ui.citieslist.screencontent.CitiesListLoadingScreenContent
import com.example.weatherapp.ui.citieslist.screencontent.CitiesListSuccessScreenContent

@Composable
fun CitiesListScreen(
    onCityClick: (City) -> Unit,
) {
    val citiesViewModel = hiltViewModel<CitiesListViewModel>()
    val state = citiesViewModel.screenState.collectAsState()
    when(val value = state.value) {
        is CitiesListScreenState.Success -> CitiesListSuccessScreenContent(
            cities = value.cities,
            onCityClick = onCityClick,
        )
        CitiesListScreenState.Error -> CitiesListErrorScreenContent(
            onEvent = citiesViewModel::onEvent
        )
        CitiesListScreenState.Loading -> CitiesListLoadingScreenContent()
    }
}
