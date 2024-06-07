package com.example.city_list.ui.screencontent

import androidx.compose.runtime.Composable
import com.example.city_list.ui.CitiesListScreenEvent
import com.example.design.components.Error

@Composable
fun CitiesListErrorScreenContent(
    onEvent: (CitiesListScreenEvent) -> Unit,
) {
    Error(
        onUpdateClick = { onEvent.invoke(CitiesListScreenEvent.Update) }
    )
}