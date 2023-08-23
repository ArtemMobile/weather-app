package com.example.weatherapp.domain.repository

import com.example.weatherapp.utils.Resource


interface WeatherRepository {

    suspend fun getWeatherData(query: String): Resource<Any>
}