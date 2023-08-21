package com.example.weatherapp.ui.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.api.models.Day
import com.example.weatherapp.data.api.models.ForecastDay
import com.example.weatherapp.databinding.WeatherCardBinding
import com.example.weatherapp.utils.getDayNameFromDate

class WeatherAdapter(
    private val context: Context,
    private var weatherList: List<ForecastDay>,
    val onClickListener: (Day) -> Unit
) : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {

    class WeatherHolder(val binding: WeatherCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        return WeatherHolder(
            WeatherCardBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        val forecastDay = weatherList[position]
        with(holder.binding) {
            textViewDay.text = "${getDayNameFromDate(forecastDay.date)}:"
            textViewConditionAndTemp.text =
                "${forecastDay.day.condition.text}, ${forecastDay.day.avgTempC}°C"
            textViewHumidity.text = "${forecastDay.day.avgHumidity}"
            textViewWindSpeed.text = "${forecastDay.day.maxWindKph} km/h"
            Glide.with(context)
                .load("https:${forecastDay.day.condition.icon}")
                .into(imageViewIcon)

            root.setOnClickListener {
                onClickListener(forecastDay.day)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(data: List<ForecastDay>) {
        this.weatherList = data
        notifyDataSetChanged()
    }
}

