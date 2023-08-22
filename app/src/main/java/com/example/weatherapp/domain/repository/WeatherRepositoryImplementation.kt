package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.api.WeatherApi
import com.example.weatherapp.data.api.models.ErrorResponse
import com.google.gson.Gson
import javax.inject.Inject

class WeatherRepositoryImplementation @Inject constructor(
    private val weatherApi: WeatherApi,
) : WeatherRepository {

    override suspend fun getWeatherData(query: String): Resource<Any> {
        return try {
            val weatherResponse = weatherApi.getWeatherData(query = query)

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