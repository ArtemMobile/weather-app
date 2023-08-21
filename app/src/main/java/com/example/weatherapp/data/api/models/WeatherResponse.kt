package com.example.weatherapp.data.api.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("forecast")
    val forecast: Forecast,
    @SerializedName("location")
    val location: Location
)