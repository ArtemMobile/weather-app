package com.example.weatherapp.di

import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.repository.WeatherRepositoryImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {

    @Binds
    @ActivityRetainedScoped
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImplementation): WeatherRepository
}