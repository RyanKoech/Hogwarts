package com.ryankoech.hogwarts.feature_home.domain.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.ryankoech.hogwarts.feature_home.core.ktx.toStringOrNull
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDtoItem

data class CharacterEntityItem(
    @Keep @SerializedName("alive") val alive: Boolean,
    @Keep @SerializedName("ancestry") val ancestry: String,
    @Keep @SerializedName("dateOfBirth") val dateOfBirth: String,
    @Keep @SerializedName("gender") val gender: String,
    @Keep @SerializedName("house") val house: String,
    @Keep @SerializedName("id") val id: String,
    @Keep @SerializedName("image") val image: String,
    @Keep @SerializedName("name") val name: String,
    @Keep @SerializedName("species") val species: String,
    @Keep @SerializedName("wizard") val wizard: Boolean,
)

fun CharacterDtoItem.toCharacterEntityItem() : CharacterEntityItem {
    return CharacterEntityItem(
        alive = this.alive ?: false,
        ancestry = this.ancestry ?: "Unknown",
        dateOfBirth = this.dateOfBirth ?: yearOfBirth.toStringOrNull() ?: "Unknown",
        gender = this.gender ?: "Unknown",
        house = this.house ?: "Unknown",
        id = this.id ?: Math.random().toString(),
        image = this.image ?: "",
        name = this.name ?: "Unknown",
        species = this.species ?: "Unknown",
        wizard = this.wizard ?: false,
    )
}