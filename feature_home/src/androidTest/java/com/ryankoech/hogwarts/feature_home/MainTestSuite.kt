package com.ryankoech.hogwarts.feature_home

import com.ryankoech.hogwarts.feature_home.presentation.HomeScreenSuccessTest
import com.ryankoech.hogwarts.feature_home.presentation.HomeScreenTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    HomeScreenTest::class,
    HomeScreenSuccessTest::class,
)
class MainTestSuite