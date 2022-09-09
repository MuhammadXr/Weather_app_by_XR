package uz.gita.weatherappbyxr.repository

import android.content.Context
import im.delight.android.location.SimpleLocation
import uz.gita.weatherappbyxr.ui.Main

object LocationRep {
    private var location : SimpleLocation? = null

    fun init(context: Context){
        if (location== null){
            location = SimpleLocation(context)
        }
    }

    fun getLocation() = location
}