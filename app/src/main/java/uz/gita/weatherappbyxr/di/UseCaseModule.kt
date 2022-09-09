package uz.gita.weatherappbyxr.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.weatherappbyxr.domain.LocationUseCase
import uz.gita.weatherappbyxr.domain.MainUseCase
import uz.gita.weatherappbyxr.domain.impl.LocationUseCaseImpl
import uz.gita.weatherappbyxr.domain.impl.MainUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindLocationUseCase(impl: LocationUseCaseImpl ): LocationUseCase

    @Binds
    fun bindMainUseCase(impl: MainUseCaseImpl) : MainUseCase
}