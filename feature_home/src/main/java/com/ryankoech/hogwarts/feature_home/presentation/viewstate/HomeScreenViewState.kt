package com.ryankoech.hogwarts.feature_home.presentation.viewstate

import com.ryankoech.hogwarts.common.presentation.utils.ScreenState
import com.ryankoech.hogwarts.feature_home.domain.entities.CharacterEntity

data class HomeScreenViewState(
    val screenState : ScreenState = ScreenState.LOADING,
    val characters : CharacterEntity = CharacterEntity(),
)
