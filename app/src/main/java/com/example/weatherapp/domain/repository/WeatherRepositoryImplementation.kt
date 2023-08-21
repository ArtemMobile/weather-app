package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.data.api.models.WeatherResponse
import javax.inject.Inject

class WeatherRepositoryImplementation @Inject constructor(
    private val weatherApi: WeatherApi,
) : WeatherRepository {

    override suspend fun getWeatherData(query: String): Resource<WeatherResponse> {
        return try {
            val weatherResponse = weatherApi.getWeatherData(query = query)
            return Resource.Success(weatherResponse)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.orEmpty())
        }
    }
}