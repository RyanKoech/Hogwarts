package com.ryankoech.hogwarts.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ryankoech.hogwarts.domain.toHogwartsCharacter
import com.ryankoech.hogwarts.feature_character.domain.entity.HogwartsCharacter
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDtoItem
import javax.inject.Inject

class MainViewModel @Inject constructor(): ViewModel() {

    var character by mutableStateOf<HogwartsCharacter?>(null)
        private set

    fun updateCharacter(characterDtoItem: CharacterDtoItem) {
        character = characterDtoItem.toHogwartsCharacter()
    }

}