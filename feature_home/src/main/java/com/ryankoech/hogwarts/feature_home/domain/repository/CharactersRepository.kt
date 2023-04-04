package com.ryankoech.hogwarts.feature_home.domain.repository

import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDto
import retrofit2.Response

interface CharactersRepository {

    suspend fun getRemoteCharacters() : Response<CharacterDto>

}