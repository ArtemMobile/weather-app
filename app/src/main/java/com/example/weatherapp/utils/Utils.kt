package com.example.weatherapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date


@SuppressLint("SimpleDateFormat")
fun getDayNameFromDate(inputDate: String): String? {
    val inFormat = SimpleDateFormat("yyyy-MM-dd")
    val date: Date? = inFormat.parse(inputDate)
    val outFormat = SimpleDateFormat("EEEE")
    return date?.let { outFormat.format(it) }
}