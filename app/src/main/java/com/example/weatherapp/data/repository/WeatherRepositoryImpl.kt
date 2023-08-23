package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.WeatherApiService
import com.example.weatherapp.data.api.models.ErrorResponse
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.utils.Resource
import com.google.gson.Gson
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApiService: WeatherApiService,
) : WeatherRepository {

    override suspend fun getWeatherData(query: String): Resource<Any> {
        return try {
            val weatherResponse = weatherApiService.getWeatherData(query = query)

            return if (weatherResponse.isSuccessful) {
                Resource.Success(weatherResponse.body()!!)
            } else {
                val errorBody = Gson().fromJson(
                    weatherResponse.errorBody()?.string().toString(),
                    ErrorResponse::class.java
                )
                Resource.Error("${errorBody.error.code}:${errorBody.error.message}")
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.orEmpty())
        }
    }
}