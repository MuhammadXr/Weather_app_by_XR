package uz.gita.weatherappbyxr.database.models.weather

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String,
)