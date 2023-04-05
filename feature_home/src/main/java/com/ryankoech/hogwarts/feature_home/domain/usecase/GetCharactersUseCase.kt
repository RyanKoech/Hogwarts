package com.ryankoech.hogwarts.feature_home.domain.usecase

import com.ryankoech.hogwarts.common.core.util.Resource
import com.ryankoech.hogwarts.feature_home.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDto
import com.ryankoech.hogwarts.feature_home.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.Response
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class GetCharactersUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL) private val repository: CharactersRepository
) {

    private var cache : CharacterDto? = null

    operator fun invoke(filterString : String = "", filterHouse : String = "",) = flow<Resource<CharacterDto>> {

        val response : Response<CharacterDto> = if(cache.isNullOrEmpty()) {
            val response = repository.getRemoteCharacters()
            cache = response.body()

            // Clear cache after 1 minute - Commented out since the information is static.
            // Timer().schedule(object : TimerTask() {
            //     override fun run() {
            //         cache = null
            //     }
            // }, 60_000)

            // Save to local
            // if(!cache.isNullOrEmpty()) {
            //     repository.saveCharacters(cache!!)
            // }
            response
        } else {
            Response.success(cache)
        }

        if(response.isSuccessful){
            val filterCharactersList = response.body()!!.filter { it.name.contains(filterString, true) }.filter { it.house.contains(filterHouse, true) }
            val characters = CharacterDto()
            characters.addAll(filterCharactersList)
            emit(
                Resource.Success(
                    characters
                )
            )
            return@flow
        }
        throw Error(response.message() ?: "Response Unsuccessful.")
    }.onStart {
        emit(Resource.Loading())
    }.catch { e ->
        Timber.e(e)
        emit(Resource.Error(e.localizedMessage ?: "Unexpected Error Occurred."))
    }

}