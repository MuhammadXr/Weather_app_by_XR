package uz.gita.weatherappbyxr.database.models.geo_code

data class Location(
    val name: String,
    val local_names: LocalNames,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String
)
