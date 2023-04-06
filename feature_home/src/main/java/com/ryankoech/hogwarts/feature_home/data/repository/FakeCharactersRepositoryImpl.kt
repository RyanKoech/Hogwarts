package com.ryankoech.hogwarts.feature_home.data.repository

import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDto
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDtoItem
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.Wand
import com.ryankoech.hogwarts.feature_home.domain.repository.CharactersRepository
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject

class FakeCharactersRepositoryImpl @Inject constructor() : CharactersRepository {

    companion object {

        val MOCK_ERROR_CHARACTERDTO_API_RESPONSE : Response<CharacterDto> = Response.error(404, "Page not found".toResponseBody(null))

        fun getFakeCharacterDto() : CharacterDto {
            val  characterDto = CharacterDto()
            characterDto.addAll(
                listOf(
                    CharacterDtoItem(
                        id ="9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
                        name ="Harry Potter",
                        alternate_names = listOf("The Boy Who Lived","The Chosen One"),
                        species ="human",
                        gender ="male",
                        house ="Gryffindor",
                        dateOfBirth ="31-07-1980",
                        yearOfBirth =1980,
                        wizard =true,
                        ancestry ="half-blood",
                        eyeColour ="green",
                        hairColour ="black",
                        wand = Wand(
                            wood ="holly",
                            core ="phoenix feather",
                            length =11.0,
                        ),
                        patronus ="stag",
                        hogwartsStudent =true,
                        hogwartsStaff =false,
                        actor ="Daniel Radcliffe",
                        alternate_actors = listOf(),
                        alive =true,
                        image ="https://ik.imagekit.io/hpapi/harry.jpg"
                    ),
                    CharacterDtoItem(
                        id ="9e3f7ce4-b9a7-4244-b709",
                        name ="Garry Potter",
                        alternate_names = listOf("The Boy Who Lived","The Chosen One"),
                        species ="human",
                        gender ="male",
                        house ="Slytherin",
                        dateOfBirth ="31-07-1980",
                        yearOfBirth =1980,
                        wizard =true,
                        ancestry ="half-blood",
                        eyeColour ="green",
                        hairColour ="black",
                        wand = Wand(
                            wood ="holly",
                            core ="phoenix feather",
                            length =11.0,
                        ),
                        patronus ="stag",
                        hogwartsStudent =true,
                        hogwartsStaff =false,
                        actor ="Daniel Radcliffe",
                        alternate_actors = listOf(),
                        alive =true,
                        image ="https://ik.imagekit.io/hpapi/harry.jpg"
                    ),
                    CharacterDtoItem(
                        id ="9e3f7ce4-b709-dae5c1f1d4a8",
                        name ="Marry Potter",
                        alternate_names = listOf("The Boy Who Lived","The Chosen One"),
                        species ="human",
                        gender ="male",
                        house ="Ravenclaw",
                        dateOfBirth ="31-07-1980",
                        yearOfBirth =1980,
                        wizard =true,
                        ancestry ="half-blood",
                        eyeColour ="green",
                        hairColour ="black",
                        wand = Wand(
                            wood ="holly",
                            core ="phoenix feather",
                            length =11.0,
                        ),
                        patronus ="stag",
                        hogwartsStudent =true,
                        hogwartsStaff =false,
                        actor ="Daniel Radcliffe",
                        alternate_actors = listOf(),
                        alive =true,
                        image ="https://ik.imagekit.io/hpapi/harry.jpg"
                    )
                )
            )
            return characterDto
        }
    }

    override suspend fun getRemoteCharacters(): Response<CharacterDto> {
        return Response.success(getFakeCharacterDto())
    }

}