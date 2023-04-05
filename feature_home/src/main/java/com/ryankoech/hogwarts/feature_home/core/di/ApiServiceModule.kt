package com.ryankoech.hogwarts.feature_home.core.di

import com.ryankoech.hogwarts.feature_home.data.data_source.remote.CharactersServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Singleton
    @Provides
    fun provideCharactersApiService(retrofit: Retrofit) : CharactersServiceApi  =
        retrofit.create(CharactersServiceApi::class.java)

}