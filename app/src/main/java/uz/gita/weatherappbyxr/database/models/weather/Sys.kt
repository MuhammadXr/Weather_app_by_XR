package uz.gita.weatherappbyxr.database.models.weather

data class Sys(
    val type: Long,
    val id: Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)