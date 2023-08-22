package com.example.weatherapp.data.api

import com.example.weatherapp.data.api.ApiConstants.DAYS
import retrofit2.http.GET
import com.example.weatherapp.data.api.ApiConstants.END_POINT
import com.example.weatherapp.data.api.ApiConstants.KEY
import com.example.weatherapp.data.api.ApiConstants.QUERY
import com.example.weatherapp.data.api.ApiKeys.API_KEY
import com.example.weatherapp.data.api.models.WeatherResponse
import retrofit2.Response
import retrofit2.http.Query

interface WeatherApi {
    @GET(END_POINT)
    suspend fun getWeatherData(
        @Query(QUERY) query: String,
        @Query(DAYS) days: String = "5",
        @Query(KEY) apiKey: String = API_KEY
    ): Response<WeatherResponse>
}