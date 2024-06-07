package com.example.city_list.internal.ui.screencontent

import androidx.compose.runtime.Composable
import com.example.city_list.internal.ui.CitiesListScreenEvent
import com.example.design.components.Error

@Composable
internal fun CitiesListErrorScreenContent(
    onEvent: (CitiesListScreenEvent) -> Unit,
) {
    Error(
        onUpdateClick = { onEvent.invoke(CitiesListScreenEvent.Update) }
    )
}