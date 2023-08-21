package com.example.weatherapp.data.api.models


import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("avghumidity")
    val avgHumidity: Int,
    @SerializedName("avgtemp_c")
    val avgTempC: Double,
    @SerializedName("condition")
    val condition: Condition,
    @SerializedName("maxwind_kph")
    val maxWindKph: Double,
)