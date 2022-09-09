package uz.gita.weatherappbyxr.domain.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.weatherappbyxr.database.models.WeatherData
import uz.gita.weatherappbyxr.database.models.forecast.ForeCast
import uz.gita.weatherappbyxr.domain.MainUseCase
import uz.gita.weatherappbyxr.repository.Repository
import uz.gita.weatherappbyxr.repository.RepositoryImpl
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor(private val repository: Repository) : MainUseCase {
    override suspend fun getWeather(lat: Double, lon: Double): Flow<WeatherData> {
        return repository.getWeather(lat, lon)
    }

    override suspend fun getForeCast(lat: Double, lon: Double): Flow<ForeCast> {
        return repository.getForecast(lat, lon)
    }
}