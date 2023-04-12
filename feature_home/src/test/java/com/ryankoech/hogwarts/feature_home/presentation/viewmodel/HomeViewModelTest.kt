package com.ryankoech.hogwarts.feature_home.presentation.viewmodel

import com.google.common.truth.Truth.assertThat
import com.ryankoech.hogwarts.common.presentation.utils.ScreenState
import com.ryankoech.hogwarts.feature_home.data.repository.FakeCharactersRepositoryImpl
import com.ryankoech.hogwarts.feature_home.domain.entities.toCharacterEntity
import com.ryankoech.hogwarts.feature_home.domain.repository.CharactersRepository
import com.ryankoech.hogwarts.feature_home.domain.usecase.GetCharactersUseCase
import com.ryankoech.hogwarts.feature_home.presentation.viewstate.HomeScreenViewState
import com.ryankoech.hogwarts.feature_home.utils.MOCK_EXCEPTION_MESSAGE
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*

import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var dispatcher : TestDispatcher

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        val getCharactersUseCase = GetCharactersUseCase(FakeCharactersRepositoryImpl())
        dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
        viewModel = HomeViewModel(getCharactersUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `viewModel shows initial state`() = runTest {
        val expectedState = HomeScreenViewState()

        val state = viewModel.viewState.value

        assertThat(state).isEqualTo(expectedState)
    }

    @Test
    fun `viewModel shows success state with data`() = runTest {
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.viewState.value

        assertThat(state.screenState).isEqualTo(ScreenState.SUCCESS)
        assertThat(state.characters).isEqualTo(FakeCharactersRepositoryImpl.getFakeCharacterDto().toCharacterEntity())
    }

    @Test
    fun `viewModel shows error state when repo throw Exception`() = runTest {
        val mockRepository = mockk<CharactersRepository>()
        coEvery { mockRepository.getRemoteCharacters() } throws Exception(MOCK_EXCEPTION_MESSAGE)

        viewModel = HomeViewModel(GetCharactersUseCase(mockRepository))
        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.viewState.value

        assertThat(state.screenState).isEqualTo(ScreenState.ERROR)
    }

    @Test
    fun `viewModel shows success state with filter name data`() = runTest {
        val filterName = FakeCharactersRepositoryImpl.getFakeCharacterDto().first().name!!

        viewModel.getCharacters(
            filterString = filterName,
            filterHouse = ""
        )


        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.viewState.value

        assertThat(state.screenState).isEqualTo(ScreenState.SUCCESS)
        assertThat(state.characters.filter { !it.name.contains(filterName) }).isEmpty()
    }

    @Test
    fun `viewModel shows success state with filter house data`() = runTest {
        val filterHouse = FakeCharactersRepositoryImpl.getFakeCharacterDto().first().house!!

        viewModel.getCharacters(
            filterString = "",
            filterHouse = filterHouse
        )


        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.viewState.value

        assertThat(state.screenState).isEqualTo(ScreenState.SUCCESS)
        assertThat(state.characters.filter { !it.house.contains(filterHouse) }).isEmpty()
    }

    @Test
    fun `viewModel shows success state with filter name and house data`() = runTest {
        val filterHouse = FakeCharactersRepositoryImpl.getFakeCharacterDto().first().house!!
        val filterName = FakeCharactersRepositoryImpl.getFakeCharacterDto().first().name!!

        viewModel.getCharacters(
            filterString = filterName,
            filterHouse = filterHouse
        )


        dispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.viewState.value

        assertThat(state.screenState).isEqualTo(ScreenState.SUCCESS)
        assertThat(state.characters.filter { !it.name.contains(filterName) }.filter { !it.house.contains(filterHouse) }).isEmpty()
    }
}