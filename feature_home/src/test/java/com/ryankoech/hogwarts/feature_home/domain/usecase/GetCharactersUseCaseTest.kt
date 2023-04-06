package com.ryankoech.hogwarts.feature_home.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.ryankoech.hogwarts.common.core.util.Resource
import com.ryankoech.hogwarts.feature_home.data.repository.FakeCharactersRepositoryImpl
import com.ryankoech.hogwarts.feature_home.data.repository.FakeCharactersRepositoryImpl.Companion.MOCK_ERROR_CHARACTERDTO_API_RESPONSE
import com.ryankoech.hogwarts.feature_home.domain.repository.CharactersRepository
import com.ryankoech.hogwarts.feature_home.utils.MOCK_EXCEPTION_MESSAGE
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class GetCharactersUseCaseTest {

    private lateinit var fakeRepository : CharactersRepository

    @Before
    fun setUp() {
        fakeRepository = FakeCharactersRepositoryImpl()
    }

    @Test
    fun  `call flow onStart - return Resource Loading`() = runTest {
        val getCharactersUseCase = GetCharactersUseCase(fakeRepository)

        getCharactersUseCase().test {
            val resource  = awaitItem()
            assertThat(resource).isInstanceOf(Resource.Loading::class.java)
            awaitItem()
            awaitComplete()
        }

    }

    @Test
    fun  `repository return Data, no filter Strings - return Resource Success and Full Data`() = runTest {
        val getCharactersUseCase = GetCharactersUseCase(fakeRepository)

        getCharactersUseCase().test {
            awaitItem()
            val resource  = awaitItem()
            assertThat(resource).isInstanceOf(Resource.Success::class.java)
            assertThat(resource.data!!.size).isEqualTo(FakeCharactersRepositoryImpl.getFakeCharacterDto().size)
            assertThat(resource.data!!.last()).isEqualTo(FakeCharactersRepositoryImpl.getFakeCharacterDto().last())
            awaitComplete()
        }

    }

    @Test
    fun  `repository return Data, with name filter Strings - return Resource Success and no character without specified name`() = runTest {
        val getCharactersUseCase = GetCharactersUseCase(fakeRepository)

        val searchName = FakeCharactersRepositoryImpl.getFakeCharacterDto().last().name
        getCharactersUseCase(
            filterString = searchName
        ).test {
            awaitItem()
            val resource  = awaitItem()
            assertThat(resource).isInstanceOf(Resource.Success::class.java)
            assertThat(resource.data!!.size).isNotEqualTo(FakeCharactersRepositoryImpl.getFakeCharacterDto().size)
            assertThat(resource.data!!.filter { it.name != searchName}).isEmpty()
            awaitComplete()
        }

    }

    @Test
    fun  `repository return Data, with house filter Strings - return Resource Success and no character without specified house`() = runTest {
        val getCharactersUseCase = GetCharactersUseCase(fakeRepository)

        val searchHouse = FakeCharactersRepositoryImpl.getFakeCharacterDto().first().house
        getCharactersUseCase(
            filterString = searchHouse
        ).test {
            awaitItem()
            val resource  = awaitItem()
            assertThat(resource).isInstanceOf(Resource.Success::class.java)
            assertThat(resource.data!!.size).isNotEqualTo(FakeCharactersRepositoryImpl.getFakeCharacterDto().size)
            assertThat(resource.data!!.filter { it.house != searchHouse}).isEmpty()
            awaitComplete()
        }

    }

    @Test
    fun  `repository return unsuccessful response - return Resource Error`() = runTest {
        val mockRepository = mockk<CharactersRepository>()
        coEvery { mockRepository.getRemoteCharacters() } returns MOCK_ERROR_CHARACTERDTO_API_RESPONSE

        val getCharactersUseCase = GetCharactersUseCase(mockRepository)

        getCharactersUseCase().test {
            awaitItem()
            val resource  = awaitItem()
            assertThat(resource).isInstanceOf(Resource.Error::class.java)
            awaitComplete()
        }

    }

    @Test
    fun  `repository return Throw Exception - return Resource Error`() = runTest {
        val mockRepository = mockk<CharactersRepository>()
        coEvery { mockRepository.getRemoteCharacters() } throws Exception(MOCK_EXCEPTION_MESSAGE)

        val getCharactersUseCase = GetCharactersUseCase(mockRepository)

        getCharactersUseCase().test {
            awaitItem()
            val resource  = awaitItem()
            assertThat(resource).isInstanceOf(Resource.Error::class.java)
            assertThat(resource.message!!).isEqualTo(MOCK_EXCEPTION_MESSAGE)
            awaitComplete()
        }

    }
}