package com.example.weatherapp.data.api.models

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("country")
    val country: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val region: String,
)