package com.ryankoech.hogwarts.domain

import com.ryankoech.hogwarts.feature_character.domain.entity.HogwartsCharacter
import com.ryankoech.hogwarts.feature_home.domain.entities.CharacterEntityItem

fun CharacterEntityItem.toHogwartsCharacter() : HogwartsCharacter {

    return HogwartsCharacter(
        id = this.id,
        name = this.name,
        species = this.species,
        gender = this.gender,
        dateOfBirth = this.dateOfBirth ,
        wizard = this.wizard,
        ancestry = this.ancestry.ifEmpty { "Unknown" },
        alive = this.alive,
        image = this.image,
    )
}