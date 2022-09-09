package uz.gita.weatherappbyxr.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.weatherappbyxr.database.models.WeatherData
import uz.gita.weatherappbyxr.database.models.forecast.ForeCast
import uz.gita.weatherappbyxr.database.models.geo_code.Location

interface Repository {
    suspend fun getWeather(lat:Double, lon:Double) : Flow<WeatherData>

    suspend fun getLocations(name:String) : Flow<List<Location>>

    suspend fun getCurrentLocation()  : Flow<List<Location>>

    suspend fun getForecast(lat:Double, lon: Double): Flow<ForeCast>
}