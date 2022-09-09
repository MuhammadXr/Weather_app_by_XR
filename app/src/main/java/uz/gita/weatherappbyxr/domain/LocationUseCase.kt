package uz.gita.weatherappbyxr.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.weatherappbyxr.database.models.WeatherData
import uz.gita.weatherappbyxr.database.models.geo_code.Location

interface LocationUseCase {

    suspend fun getLocations(name:String) : Flow<List<Location>>

    suspend fun getCurrentLocation()  : Flow<List<Location>>

}