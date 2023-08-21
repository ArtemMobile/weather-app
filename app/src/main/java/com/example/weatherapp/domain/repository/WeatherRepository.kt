package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.api.models.WeatherResponse

interface WeatherRepository {

    suspend fun getWeatherData(query: String): Resource<WeatherResponse>
}