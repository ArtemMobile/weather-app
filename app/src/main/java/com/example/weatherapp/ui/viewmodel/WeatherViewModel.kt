package com.example.weatherapp.ui.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.repository.Resource
import com.example.weatherapp.domain.repository.WeatherRepositoryImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepositoryImplementation: WeatherRepositoryImplementation) :
    ViewModel() {

    private var _getWeatherState: MutableLiveData<Resource<Any>> =
        MutableLiveData(Resource.Loading())
    val getWeatherState: MutableLiveData<Resource<Any>> = _getWeatherState

    fun getWeather(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = weatherRepositoryImplementation.getWeatherData(query)
            withContext(Dispatchers.Main) {
                _getWeatherState.value = response
            }
        }
    }
}
