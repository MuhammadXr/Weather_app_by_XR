package uz.gita.weatherappbyxr.database.network

import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.weatherappbyxr.database.models.WeatherData
import uz.gita.weatherappbyxr.database.models.forecast.ForeCast

interface WeatherApi {

    @GET("weather/")
    suspend fun getWeather(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("appid") key: String, @Query("units") units:String = "metric") : WeatherData

    @GET("forecast/")
    suspend fun getForecast(@Query("lat") lat:Double, @Query("lon") lon: Double, @Query("appid") key: String, @Query("units") units:String = "metric") : ForeCast
}