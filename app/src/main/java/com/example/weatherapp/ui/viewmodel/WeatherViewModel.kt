package com.example.weatherapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.utils.Resource
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val getWeatherUseCase: GetWeatherUseCase) :
    ViewModel() {

    private var _getWeatherState: MutableLiveData<Resource<Any>> =
        MutableLiveData(Resource.Loading())
    val getWeatherState: MutableLiveData<Resource<Any>> = _getWeatherState

    fun getWeather(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getWeatherUseCase.execute(query)
            withContext(Dispatchers.Main) {
                _getWeatherState.value = response
            }
        }
    }
}
