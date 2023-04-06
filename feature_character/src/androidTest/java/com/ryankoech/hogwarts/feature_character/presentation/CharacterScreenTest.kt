package com.ryankoech.hogwarts.feature_character.presentation

import androidx.activity.ComponentActivity
import androidx.compose.material.Surface
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.ryankoech.hogwarts.common.core.util.Resource
import com.ryankoech.hogwarts.common.presentation.components.TEST_TAG_LOADING_SCREEN
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme
import com.ryankoech.hogwarts.feature_character.domain.entity.HogwartsCharacter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterScreenTest {

    private lateinit var character: HogwartsCharacter

    @Before
    fun setUp() {
        character = HogwartsCharacter(
            id ="9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8",
            name ="Harry Potter",
            species ="human",
            gender ="male",
            dateOfBirth ="31-07-1980",
            wizard =true,
            ancestry ="half-blood",
            alive =true,
            image ="https://ik.imagekit.io/hpapi/harry.jpg"
        )


        composeTestRule.setContent {
            HogwartsTheme {
                Surface {
                    CharacterScreen(
                        character = character
                    )
                }
            }
        }
    }

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun beginFetchCoins_ShowLoadingScreen() {

        composeTestRule.onNodeWithText(
            character.name,
            ignoreCase = true
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            character.species,
            ignoreCase = true
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            character.dateOfBirth,
            ignoreCase = true
        ).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            character.ancestry,
            ignoreCase = true
        ).assertIsDisplayed()

    }

}