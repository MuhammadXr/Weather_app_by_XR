package uz.gita.weatherappbyxr.database.models

import uz.gita.weatherappbyxr.database.models.geo_code.Location

data class GeoResponseData(
    val countries: List<Location>
)
