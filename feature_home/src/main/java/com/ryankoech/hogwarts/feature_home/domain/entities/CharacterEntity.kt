package com.ryankoech.hogwarts.feature_home.domain.entities

import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDto

fun CharacterDto.toCharacterEntity() : CharacterEntity {
    val characterEntity = CharacterEntity()
    characterEntity.addAll(
        this.map{
            it.toCharacterEntityItem()
        }
    )

    return characterEntity
}

class CharacterEntity : ArrayList<CharacterEntityItem>()