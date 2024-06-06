package com.example.weatherapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.weatherapp.ui.citieslist.CitiesListDestination
import com.example.weatherapp.ui.citieslist.CitiesListScreen
import com.example.weatherapp.ui.cityweather.CityWeatherDestination
import com.example.weatherapp.ui.cityweather.CityWeatherScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    WeatherNavHost(navController = navController)
                }
            }
        }
    }
}

@Composable
private fun WeatherNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = CitiesListDestination,
    ) {
        composable<CitiesListDestination> { navBackStackEntry ->
            CitiesListScreen(
                onCityClick = { city ->
                    navController.navigateSingleTopTo(CityWeatherDestination(
                        cityName = city.name,
                        latitude = city.latitude,
                        longitude = city.longitude,
                    ))
                }
            )
        }

        composable<CityWeatherDestination> { navBackStackEntry ->
            val route = navBackStackEntry.toRoute<CityWeatherDestination>()
            CityWeatherScreen(
                cityName = route.cityName,
                latitude = route.latitude,
                longitude = route.longitude,
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: Any) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
    }
