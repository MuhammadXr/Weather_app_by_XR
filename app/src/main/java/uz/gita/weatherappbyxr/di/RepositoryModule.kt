package uz.gita.weatherappbyxr.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.weatherappbyxr.repository.Repository
import uz.gita.weatherappbyxr.repository.RepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bind(impl: RepositoryImpl): Repository
}