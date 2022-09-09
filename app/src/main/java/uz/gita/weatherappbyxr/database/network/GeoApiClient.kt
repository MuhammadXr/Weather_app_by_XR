package uz.gita.weatherappbyxr.database.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GeoApiClient {
    val geoInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val BASE_URL = "http://api.openweathermap.org/geo/1.0/"

    private val client = OkHttpClient.Builder()
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getGeoApi() = retrofit.create(GeoApi::class.java)
}