package com.example.newsapp.di

import android.app.Application
import com.example.newsapp.data.manager.LocalUserManagerImplementation
import com.example.newsapp.domain.manager.LocalUserManager
import com.example.newsapp.domain.usecases.AppEntryUsecases
import com.example.newsapp.domain.usecases.ReadAppEntry
import com.example.newsapp.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImplementation(application)

    @Provides
    @Singleton
    fun provideAppEntryUsecases(
        localUserManager: LocalUserManager
    ) = AppEntryUsecases(
        readAppEntry = ReadAppEntry(localUserManager = localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

}