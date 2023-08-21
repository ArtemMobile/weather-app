package com.example.weatherapp.data.api.models


import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("code")
    val code: Int,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("text")
    val text: String
)