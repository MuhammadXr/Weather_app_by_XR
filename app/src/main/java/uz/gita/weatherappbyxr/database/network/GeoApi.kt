package uz.gita.weatherappbyxr.database.network

import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.weatherappbyxr.database.models.geo_code.Location

interface GeoApi {
    @GET("direct")
    suspend fun getLocations(@Query("q") name:String, @Query("limit") limit:Long, @Query("appid") key:String): List<Location>

    @GET("reverse")
    suspend fun getCurrentLocation(@Query("lat") lat:Double, @Query("lon") lon: Double, @Query("limit") limit:Long, @Query("appid") key:String) : List<Location>
}