package uz.gita.weatherappbyxr.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.weatherappbyxr.database.network.GeoApi
import uz.gita.weatherappbyxr.database.network.WeatherApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun provideWeatherApiClient() : WeatherApi {

        val BASE_URL = "https://api.openweathermap.org/data/2.5/"

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGeoApiClient():  GeoApi {
        val BASE_URL = "http://api.openweathermap.org/geo/1.0/"

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GeoApi::class.java)
    }


}