package uz.gita.weatherappbyxr.domain.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.weatherappbyxr.database.models.geo_code.Location
import uz.gita.weatherappbyxr.domain.LocationUseCase
import uz.gita.weatherappbyxr.repository.Repository
import uz.gita.weatherappbyxr.repository.RepositoryImpl
import javax.inject.Inject

class LocationUseCaseImpl @Inject constructor(private val repository: Repository) : LocationUseCase {
    override suspend fun getLocations(name: String): Flow<List<Location>> {
        return repository.getLocations(name)
    }

    override suspend fun getCurrentLocation(): Flow<List<Location>> {
        return repository.getCurrentLocation()
    }
}