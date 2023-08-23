package com.example.weatherapp.ui.view.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.weatherapp.data.api.models.Day
import com.example.weatherapp.data.api.models.Location
import com.example.weatherapp.data.api.models.WeatherResponse
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.utils.Resource
import com.example.weatherapp.ui.view.adapter.WeatherAdapter
import com.example.weatherapp.ui.viewmodel.WeatherViewModel
import com.example.weatherapp.utils.Constants.ERROR
import com.example.weatherapp.utils.Constants.KAZAN
import dagger.hilt.android.AndroidEntryPoint
import com.example.weatherapp.utils.showDialog

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherViewModel.getWeather(KAZAN)
        setObservers()
        initAdapter()
        applyRecycler()
        setContentView(binding.root)
    }


    private fun setObservers() {
        weatherViewModel.getWeatherState.observe(this) { result ->
            when (result) {
                is Resource.Error -> {
                    showDialog(ERROR, result.message.toString()) {
                        weatherViewModel.getWeather(KAZAN)
                    }
                }

                is Resource.Loading -> {
                    showProgress()
                }

                is Resource.Success -> {
                    hideProgress()
                    (result.data as WeatherResponse).apply {
                        bindLocation(location)
                        this.forecast.forecastDay.let {
                            weatherAdapter.updateData(it)
                            bindDataForSingleDay(it[0].day)
                        }
                    }
                }
            }
        }
    }

    private fun bindLocation(location: Location) {
        binding.textViewLocation.text = "${location.name}, ${location.region} "
    }

    private fun bindDataForSingleDay(forecastDay: Day) {
        with(binding) {
            textViewConditionAndTemp.text =
                "${forecastDay.condition.text}, ${forecastDay.avgTempC}°C"
            textViewHumidity.text = "${forecastDay.avgHumidity}"
            textViewWindSpeed.text = "${forecastDay.maxWindKph} km/h"
            Glide.with(this@MainActivity)
                .load("https:${forecastDay.condition.icon}")
                .into(imageViewIcon)
        }
    }

    private fun showProgress() {
        binding.progressBarLoading.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressBarLoading.visibility = View.GONE
    }

    private fun initAdapter() {
        weatherAdapter = WeatherAdapter(this, mutableListOf()) {
            bindDataForSingleDay(it)
            binding.root.smoothScrollTo(0, 0)
        }
    }

    private fun applyRecycler() {
        with(binding.recyclerViewWeather) {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}