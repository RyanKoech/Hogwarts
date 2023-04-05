package com.ryankoech.hogwarts.feature_home

import com.ryankoech.hogwarts.feature_home.domain.usecase.GetCharactersUseCaseTest
import com.ryankoech.hogwarts.feature_home.presentation.viewmodel.HomeViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    HomeViewModelTest::class,
    GetCharactersUseCaseTest::class
)
class MainTestSuite