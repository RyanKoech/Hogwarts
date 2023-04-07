package com.ryankoech.hogwarts.feature_home.domain.usecase

import com.ryankoech.hogwarts.common.core.util.Resource
import com.ryankoech.hogwarts.feature_home.core.di.HILT_NAME_REPO_FOR_ALL
import com.ryankoech.hogwarts.feature_home.domain.entities.CharacterEntity
import com.ryankoech.hogwarts.feature_home.domain.entities.toCharacterEntity
import com.ryankoech.hogwarts.feature_home.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class GetCharactersUseCase @Inject constructor(
    @Named(HILT_NAME_REPO_FOR_ALL) private val repository: CharactersRepository
) {

    private var cache : CharacterEntity? = null

    operator fun invoke(filterString : String = "", filterHouse : String = "",) = flow<Resource<CharacterEntity>> {

        cache?.apply {
            emit(
                Resource.Success(
                    this.performFilter(filterString, filterHouse)
                )
            )
            return@flow
        }
        val response = repository.getRemoteCharacters()

        if(response.isSuccessful){
            cache = response.body()!!.toCharacterEntity()

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

            emit(
                Resource.Success(
                    cache!!.performFilter(filterString, filterHouse)
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

    private fun CharacterEntity.performFilter(filterString : String, filterHouse : String) : CharacterEntity {
        val characters = CharacterEntity()
        characters.addAll(
            this.filter { it.name.contains(filterString, true) }.filter { it.house.contains(filterHouse, true) }
        )
        return characters
    }

}