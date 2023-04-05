package com.ryankoech.hogwarts.feature_character.domain.entity

data class HogwartsCharacter(
    val alive: Boolean,
    val ancestry: String,
    val dateOfBirth: String,
    val gender: String,
    val id: String,
    val image: String,
    val name: String,
    val species: String,
    val wizard: Boolean,
)
