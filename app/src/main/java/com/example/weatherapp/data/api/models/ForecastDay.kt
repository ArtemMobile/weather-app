package com.example.weatherapp.data.api.models


import com.google.gson.annotations.SerializedName

data class ForecastDay(
    @SerializedName("date")
    val date: String,
    @SerializedName("day")
    val day: Day,
)