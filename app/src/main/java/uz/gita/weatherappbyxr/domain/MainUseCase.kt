package uz.gita.weatherappbyxr.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.weatherappbyxr.database.models.WeatherData
import uz.gita.weatherappbyxr.database.models.forecast.ForeCast

interface MainUseCase {
    suspend fun getWeather(lat:Double, lon:Double) : Flow<WeatherData>

    suspend fun getForeCast(lat:Double, lon:Double) : Flow<ForeCast>
}