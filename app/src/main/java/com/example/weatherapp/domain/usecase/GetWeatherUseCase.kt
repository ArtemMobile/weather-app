package com.example.weatherapp.domain.usecase

import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val weatherRepositoryImpl: WeatherRepositoryImpl) {

     suspend fun execute(query: String) = weatherRepositoryImpl.getWeatherData(query)
}