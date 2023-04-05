package com.ryankoech.hogwarts.feature_home.presentation.viewstate

import com.ryankoech.hogwarts.common.presentation.utils.ScreenState
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDto

data class HomeScreenViewState(
    val screenState : ScreenState = ScreenState.LOADING,
    val characters : CharacterDto = CharacterDto(),
)
