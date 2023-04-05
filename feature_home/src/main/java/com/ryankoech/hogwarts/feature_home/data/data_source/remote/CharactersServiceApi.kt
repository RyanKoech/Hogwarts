package com.ryankoech.hogwarts.feature_home.data.data_source.remote

import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDto
import retrofit2.Response
import retrofit2.http.GET

interface CharactersServiceApi {

    @GET("characters")
    suspend fun getCharacters() : Response<CharacterDto>

}