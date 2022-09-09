package uz.gita.weatherappbyxr

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.gita.weatherappbyxr.repository.LocationRep

@HiltAndroidApp
class App:Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        LocationRep.init(this)
    }
}