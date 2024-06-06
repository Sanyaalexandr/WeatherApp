package com.example.weatherapp.ui.citieslist.screencontent

import androidx.compose.runtime.Composable
import com.example.weatherapp.ui.citieslist.CitiesListScreenEvent
import com.example.weatherapp.ui.components.Error

@Composable
fun CitiesListErrorScreenContent(
    onEvent: (CitiesListScreenEvent) -> Unit,
) {
    Error(
        onUpdateClick = { onEvent.invoke(CitiesListScreenEvent.Update) }
    )
}