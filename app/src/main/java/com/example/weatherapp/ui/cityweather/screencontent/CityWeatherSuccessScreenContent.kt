package com.example.weatherapp.ui.cityweather.screencontent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.ui.cityweather.CityWeather
import com.example.weatherapp.ui.cityweather.CityWeatherScreenEvent

@Composable
fun CityWeatherSuccessScreenContent(
    weather: CityWeather,
    onEvent: (CityWeatherScreenEvent) -> Unit
) {
    val updateText = stringResource(id = R.string.update)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeContent)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )
        Text(
            text = "${weather.temp}°C",
            fontSize = 57.sp,
            lineHeight = 64.sp,
        )
        Text(
            text = weather.city,
            fontSize = 32.sp,
            lineHeight = 40.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = {
            onEvent.invoke(CityWeatherScreenEvent.Update)
        }) {
            Text(text = updateText)
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
        )
    }
}