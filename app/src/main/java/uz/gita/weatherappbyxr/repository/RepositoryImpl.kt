package uz.gita.weatherappbyxr.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import uz.gita.weatherappbyxr.database.models.WeatherData
import uz.gita.weatherappbyxr.database.models.forecast.ForeCast
import uz.gita.weatherappbyxr.database.models.geo_code.Location
import uz.gita.weatherappbyxr.database.network.GeoApi
import uz.gita.weatherappbyxr.database.network.GeoApiClient
import uz.gita.weatherappbyxr.database.network.WeatherApi
import uz.gita.weatherappbyxr.database.network.WeatherApiClient
import uz.gita.weatherappbyxr.utils.hasConnection
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val geoApi: GeoApi, private val weatherApi: WeatherApi)
    : Repository{
    private val location = LocationRep.getLocation()


    private val errorLiveData = MutableLiveData<String>()

    private var lat: Double = 0.0
    private var lon: Double = 0.0

    private val ApiKey = "c0cb190b098d79addead28cf7d1e5f59"

    val weatherData : WeatherData? = null

    override suspend fun getWeather(lat:Double, lon:Double) : Flow<WeatherData> = flow {
        if (hasConnection()){
            val result = weatherApi.getWeather(lat,lon, ApiKey)
            emit(result)
        }
        else{
            errorLiveData.postValue("Internet yo'q")
        }
    }

    override suspend fun getLocations(name:String) : Flow<List<Location>> = flow{
        if (hasConnection()) {
            val result = geoApi.getLocations(name, 10, ApiKey)


                emit(result)

        }
        else{
            errorLiveData.postValue("Internet yo'q")
        }
    }

    override suspend fun getCurrentLocation()  : Flow<List<Location>> = flow {

        if (location!!.hasLocationEnabled() && hasConnection()){
            val curLocation = withContext(Dispatchers.IO){
                location.position
            }
            delay(200)
            if (curLocation != null){
                lat = curLocation.latitude
                lon = curLocation.longitude
            }

            Log.d("TTT", "$lat  $lon")
            val result = withContext(Dispatchers.IO){
                geoApi.getCurrentLocation(lat, lon, 10, ApiKey)
            }

            emit(result)

        }
        else
        {
            errorLiveData.postValue("GPS va Internet o'chiq")
        }

    }.flowOn(Dispatchers.IO)

    override suspend fun getForecast(lat: Double, lon: Double): Flow<ForeCast> = flow {
        if (hasConnection()){
            val result = weatherApi.getForecast(lat,lon, ApiKey)

            emit(result)
        }
    }.flowOn(Dispatchers.IO)

}