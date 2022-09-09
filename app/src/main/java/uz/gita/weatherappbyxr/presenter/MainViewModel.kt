package uz.gita.weatherappbyxr.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.weatherappbyxr.database.models.WeatherData
import uz.gita.weatherappbyxr.database.models.forecast.ForeCast
import uz.gita.weatherappbyxr.domain.MainUseCase
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val rep: MainUseCase):ViewModel(){

    val weatherLive = MutableLiveData<WeatherData>()
    val foreCastLive = MutableLiveData<ForeCast>()
    val gotoLocations = MutableLiveData<Unit>()


    fun getWeather(lat:Double, lon:Double){
        viewModelScope.launch(Dispatchers.IO){
            rep.getWeather(lat,lon).onEach {
                weatherLive.postValue(it)
            }.collect()
        }
    }

    fun gotoLocations(){
        gotoLocations.value = Unit
    }

    fun getForecast(lat:Double, lon: Double){
        viewModelScope.launch(Dispatchers.IO){
            rep.getForeCast(lat,lon).onEach {
                foreCastLive.postValue(it)
            }.collect()
        }
    }

}