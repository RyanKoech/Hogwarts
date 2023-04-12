package com.ryankoech.hogwarts.feature_home.presentation

import androidx.activity.ComponentActivity
import androidx.compose.material.Surface
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme
import com.ryankoech.hogwarts.feature_home.data.repository.FakeCharactersRepositoryImpl
import com.ryankoech.hogwarts.feature_home.domain.entities.CharacterEntity
import com.ryankoech.hogwarts.feature_home.domain.entities.toCharacterEntity
import com.ryankoech.hogwarts.feature_home.presentation.components.TEST_TAG_SEARCHBAR_FILTER_ICON
import io.mockk.junit4.MockKRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenSuccessTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @get:Rule
    val mockkRule = MockKRule(this)

    private lateinit var characterDto : CharacterEntity

    @Before
    fun setUp() {

        characterDto = FakeCharactersRepositoryImpl.getFakeCharacterDto().toCharacterEntity()

        composeTestRule.setContent {
            HogwartsTheme {
                Surface {
                    HomeScreenSuccess(
                        navigateToCharacterScreen = {},
                        characters = characterDto,
                        searchBarValue = "",
                        onSearchBarValueChange = {},
                        housesList = HOUSES_MAP.toList().map { it.first },
                        selectedHouse = HOUSES_MAP.entries.iterator().next().key,
                        changeSelectedHouse = {}
                    )
                }
            }
        }

    }

    @After
    fun tearDown() {
    }

    @Test
    fun receiveCharacterDtoList_displayList() {

        composeTestRule.onNodeWithText(characterDto.first().name).assertIsDisplayed()

    }

    @Test
    fun clickFilterIcon_DisplayModalWithHousesList() {

        composeTestRule.onNodeWithTag(TEST_TAG_SEARCHBAR_FILTER_ICON).performClick()

        composeTestRule.onNodeWithText(
            text = "Select House",
            substring = true,
            ignoreCase = true,
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            text = HOUSES_MAP.entries.iterator().next().key,
            substring = true,
            ignoreCase = true,
        ).assertIsDisplayed()

    }

    @Test
    fun clickCloseIcon_hideModal() {

        composeTestRule.onNodeWithTag(TEST_TAG_SEARCHBAR_FILTER_ICON).performClick()

        composeTestRule.onNodeWithTag(TEST_TAG_HOME_SCREEN_SUCCESS_CLOSE_MODAL_ICON).performClick()

        composeTestRule.onNodeWithText(
            text = "Select House",
            substring = true,
            ignoreCase = true,
        ).assertDoesNotExist()

    }
}