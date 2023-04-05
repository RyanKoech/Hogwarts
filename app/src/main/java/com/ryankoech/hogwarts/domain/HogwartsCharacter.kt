package com.ryankoech.hogwarts.domain

import com.ryankoech.hogwarts.feature_character.domain.entity.HogwartsCharacter
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDtoItem

fun CharacterDtoItem.toHogwartsCharacter() : HogwartsCharacter {

    return HogwartsCharacter(
        id = this.id,
        name = this.name,
        species = this.species,
        gender = this.gender,
        dateOfBirth = this.dateOfBirth,
        wizard = this.wizard,
        ancestry = this.ancestry,
        alive = this.alive,
        image = this.image,
    )
}