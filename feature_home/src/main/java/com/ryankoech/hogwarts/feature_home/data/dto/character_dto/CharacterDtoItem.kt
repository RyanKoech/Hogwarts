package com.ryankoech.hogwarts.feature_home.data.dto.character_dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class CharacterDtoItem(
    @Keep @SerializedName("actor") val actor: String?,
    @Keep @SerializedName("alive") val alive: Boolean?,
    @Keep @SerializedName("alternate_actors") val alternate_actors: List<String?>?,
    @Keep @SerializedName("alternate_names") val alternate_names: List<String?>?,
    @Keep @SerializedName("ancestry") val ancestry: String?,
    @Keep @SerializedName("dateOfBirth") val dateOfBirth: String?,
    @Keep @SerializedName("eyeColour") val eyeColour: String?,
    @Keep @SerializedName("gender") val gender: String?,
    @Keep @SerializedName("hairColour") val hairColour: String?,
    @Keep @SerializedName("hogwartsStaff") val hogwartsStaff: Boolean?,
    @Keep @SerializedName("hogwartsStudent") val hogwartsStudent: Boolean?,
    @Keep @SerializedName("house") val house: String?,
    @Keep @SerializedName("id") val id: String?,
    @Keep @SerializedName("image") val image: String?,
    @Keep @SerializedName("name") val name: String?,
    @Keep @SerializedName("patronus") val patronus: String?,
    @Keep @SerializedName("species") val species: String?,
    @Keep @SerializedName("wand") val wand: Wand?,
    @Keep @SerializedName("wizard") val wizard: Boolean?,
    @Keep @SerializedName("yearOfBirth") val yearOfBirth: Int?
)