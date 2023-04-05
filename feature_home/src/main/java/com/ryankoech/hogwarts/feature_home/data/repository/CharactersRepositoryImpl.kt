package com.ryankoech.hogwarts.feature_home.data.repository

import com.ryankoech.hogwarts.feature_home.data.data_source.remote.CharactersServiceApi
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDto
import com.ryankoech.hogwarts.feature_home.domain.repository.CharactersRepository
import retrofit2.Response
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val api : CharactersServiceApi,
) : CharactersRepository {
    override suspend fun getRemoteCharacters(): Response<CharacterDto> = api.getCharacters()
}