package com.example.city_list.data.service

import com.example.city_list.data.model.CityDTO
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

interface CitiesService {
    suspend fun getCities(): Result<List<CityDTO>>

    companion object {
        fun create(): CitiesService {
            return CitiesServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}

class CitiesServiceImpl(
    private val client: HttpClient,
): CitiesService {
    override suspend fun getCities(): Result<List<CityDTO>> {
        return try {
            val jsonString = client.get<String> {
                url(CITIES_URL)
            }
            val cities = Json.decodeFromString(
                deserializer = ListSerializer(CityDTO.serializer()),
                string = jsonString
            )
            Result.success(cities)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

private const val CITIES_URL = "https://gist.githubusercontent.com/Stronger197/764f9886a1e8392ddcae2521437d5a3b/raw/65164ea1af958c75c81a7f0221bead610590448e/cities.json"