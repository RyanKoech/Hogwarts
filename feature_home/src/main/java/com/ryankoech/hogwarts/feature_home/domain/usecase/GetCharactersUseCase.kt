package com.ryankoech.hogwarts.feature_home.domain.usecase

import com.ryankoech.hogwarts.common.core.util.Resource
import com.ryankoech.hogwarts.feature_home.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDto
import com.ryankoech.hogwarts.feature_home.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class GetCharactersUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL) private val repository: CharactersRepository
) {

    operator fun invoke() = flow<Resource<CharacterDto>> {
        val response = repository.getRemoteCharacters()

        if(response.isSuccessful){
            emit(Resource.Success(response.body()!!))
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