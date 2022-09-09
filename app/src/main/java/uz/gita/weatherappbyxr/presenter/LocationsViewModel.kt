package uz.gita.weatherappbyxr.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.weatherappbyxr.database.models.geo_code.Location
import uz.gita.weatherappbyxr.domain.LocationUseCase
import javax.inject.Inject


@HiltViewModel
class LocationsViewModel @Inject constructor(private val rep: LocationUseCase) :ViewModel(){


    val locationLiveData = MutableLiveData<Location>()
    val listLocations = MutableLiveData<List<Location>>()

    fun getLocations(name:String){
        viewModelScope.launch (Dispatchers.IO){
            rep.getLocations(name).onEach {
                listLocations.postValue(it)
            }.collect()
        }
    }

    fun getCurrentLocation(){
        viewModelScope.launch(Dispatchers.IO){
            rep.getCurrentLocation().onEach {
                listLocations.postValue(it)
            }.collect()
        }
    }

    fun setLocationData(location: Location){
        locationLiveData.value = location
    }
}