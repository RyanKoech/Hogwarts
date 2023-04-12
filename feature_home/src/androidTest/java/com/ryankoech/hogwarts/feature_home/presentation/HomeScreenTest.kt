package com.ryankoech.hogwarts.feature_home.presentation

import androidx.activity.ComponentActivity
import androidx.compose.material.Surface
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.ryankoech.hogwarts.common.core.util.Resource
import com.ryankoech.hogwarts.common.presentation.components.TEST_TAG_ERROR_SCREEN
import com.ryankoech.hogwarts.common.presentation.components.TEST_TAG_LOADING_SCREEN
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme
import com.ryankoech.hogwarts.feature_home.data.repository.FakeCharactersRepositoryImpl
import com.ryankoech.hogwarts.feature_home.domain.entities.CharacterEntity
import com.ryankoech.hogwarts.feature_home.domain.entities.toCharacterEntity
import com.ryankoech.hogwarts.feature_home.domain.usecase.GetCharactersUseCase
import com.ryankoech.hogwarts.feature_home.presentation.components.TEST_TAG_SEARCHBAR
import com.ryankoech.hogwarts.feature_home.presentation.viewmodel.HomeViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    private lateinit var viewModel : HomeViewModel

    private lateinit var charactersFlows : Flow<Resource<CharacterEntity>>

    // private lateinit var dispatcher: CoroutineDispatcher

    @Before
    fun setUp() {
        // dispatcher = Dispatchers.Unconfined
        // Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        // Dispatchers.resetMain()
    }

    @Test
    fun beginFetchCoins_ShowLoadingScreen() {
        charactersFlows = flow {
            emit(Resource.Loading())
        }

        coEvery { getCharactersUseCase(any(), any()) } returns charactersFlows

        viewModel = HomeViewModel(getCharactersUseCase)

        composeTestRule.setContent {
            HogwartsTheme {
                Surface {
                    HomeScreen(
                        navigateToCharacterScreen = {},
                        viewModel = viewModel,
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag(TEST_TAG_LOADING_SCREEN).assertIsDisplayed()

    }

    @Test
    fun fetchCoins_ShowSuccessScreen() {
        charactersFlows = flow {
            emit(Resource.Success(FakeCharactersRepositoryImpl.getFakeCharacterDto().toCharacterEntity()))
        }

        coEvery { getCharactersUseCase(any(), any()) } returns charactersFlows

        viewModel = HomeViewModel(getCharactersUseCase)


        composeTestRule.setContent {
            HogwartsTheme {
                Surface {
                    HomeScreen(
                        navigateToCharacterScreen = {},
                        viewModel = viewModel,
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag(TEST_TAG_HOME_SCREEN_SUCCESS).assertIsDisplayed()

    }

    @Test
    fun fetchCoinsWithUseCaseError_ShowErrorScreen() {
        charactersFlows = flow {
            emit(Resource.Error(""))
        }

        coEvery { getCharactersUseCase(any(), any()) } returns charactersFlows

        viewModel = HomeViewModel(getCharactersUseCase)

        composeTestRule.setContent {
            HogwartsTheme {
                Surface {
                    HomeScreen(
                        navigateToCharacterScreen = {},
                        viewModel = viewModel,
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag(TEST_TAG_ERROR_SCREEN).assertIsDisplayed()

    }

    @Test
    fun searchCharacterName_ShowNoOtherCharacterName() {
        val characterDto = FakeCharactersRepositoryImpl.getFakeCharacterDto().toCharacterEntity()
        val getCharactersUseCase = GetCharactersUseCase(FakeCharactersRepositoryImpl())

        viewModel = HomeViewModel(getCharactersUseCase)


        composeTestRule.setContent {
            HogwartsTheme {
                Surface {
                    HomeScreen(
                        navigateToCharacterScreen = {},
                        viewModel = viewModel,
                    )
                }
            }
        }

        composeTestRule.onNodeWithText(characterDto.last().name).assertExists()

        composeTestRule.onNodeWithTag(TEST_TAG_SEARCHBAR).performTextInput(characterDto.first().name)

        composeTestRule.onNodeWithText(characterDto.last().name).assertDoesNotExist()

    }
}