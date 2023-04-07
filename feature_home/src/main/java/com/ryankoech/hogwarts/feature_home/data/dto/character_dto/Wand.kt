package com.ryankoech.hogwarts.feature_home.data.dto.character_dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class Wand(
    @Keep @SerializedName("core") val core: String?,
    @Keep @SerializedName("length") val length: Double?,
    @Keep @SerializedName("wood") val wood: String?
)