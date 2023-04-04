package com.ryankoech.hogwarts.feature_home.core.di

import com.ryankoech.hogwarts.feature_home.data.repository.CharactersRepositoryImpl
import com.ryankoech.hogwarts.feature_home.data.repository.FakeCharactersRepositoryImpl
import com.ryankoech.hogwarts.feature_home.domain.repository.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

const val HILT_NAMED_REPO_FAKE = "FakeRepo"
const val HILT_NAMED_REPO = "Repo"
const val HILT_NAME_REPO_FOR_ALL = HILT_NAMED_REPO

@Module
@InstallIn(SingletonComponent::class)
abstract class CharactersRepositoryModule {

    @Binds
    @Singleton
    @Named(HILT_NAMED_REPO)
    abstract fun provideHomeRepository(
        repositoryModule: CharactersRepositoryImpl
    ) : CharactersRepository

    @Binds
    @Singleton
    @Named(HILT_NAMED_REPO_FAKE)
    abstract fun provideFakeHomeRepository(
        repository: FakeCharactersRepositoryImpl
    ) : CharactersRepository
}