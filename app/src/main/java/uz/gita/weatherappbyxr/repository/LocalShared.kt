package uz.gita.weatherappbyxr.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE

class LocalShared private constructor(context: Context){
    private val sharedPreferences = context.getSharedPreferences("LOCAL", MODE_PRIVATE)
    private val editor = sharedPreferences.edit()


}