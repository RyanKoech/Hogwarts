package com.ryankoech.hogwarts.feature_home.presentation

import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ryankoech.hogwarts.common.presentation.components.ErrorScreen
import com.ryankoech.hogwarts.common.presentation.components.LoadingScreen
import com.ryankoech.hogwarts.common.presentation.components.TEST_TAG_ERROR_SCREEN
import com.ryankoech.hogwarts.common.presentation.components.TEST_TAG_LOADING_SCREEN
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme
import com.ryankoech.hogwarts.common.presentation.utils.ScreenState
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDtoItem
import com.ryankoech.hogwarts.feature_home.presentation.viewmodel.HomeViewModel

val HOUSES_MAP : Map<String, String> = hashMapOf(
    "All" to "",
    "Gryffindor" to "gryffindor",
    "Slytherin" to "slytherin",
    "Ravenclaw" to "ravenclaw",
    "Hufflepuff" to "hufflepuff",
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel : HomeViewModel = hiltViewModel(),
    navigateToCharacterScreen : (CharacterDtoItem) -> Unit
) {
    val viewState = viewModel.viewState.value

    var filterStringState by remember {
        mutableStateOf("")
    }

    var filterHouseState by remember {
        mutableStateOf(HOUSES_MAP.entries.iterator().next().key)
    }

    fun editFilterStringState(subString :String) {
        viewModel.getCharacters(subString, HOUSES_MAP[filterHouseState] ?: "")
        filterStringState = subString
    }


    when(viewState.screenState) {
        ScreenState.SUCCESS -> {
            HomeScreenSuccess(
                modifier = modifier
                    .testTag(TEST_TAG_HOME_SCREEN_SUCCESS),
                navigateToCharacterScreen = navigateToCharacterScreen,
                characters = viewState.characters,
                searchBarValue = filterStringState,
                onSearchBarValueChange = ::editFilterStringState,
                housesList = HOUSES_MAP.toList().map { it.first },
                selectedHouse = filterHouseState,
                changeSelectedHouse = {
                    viewModel.getCharacters(filterStringState, HOUSES_MAP[it] ?: "")
                    filterHouseState = it
                }
            )
        }
        ScreenState.LOADING -> {
            LoadingScreen(
                modifier = modifier
                    .testTag(TEST_TAG_LOADING_SCREEN),
            )
        }
        ScreenState.ERROR -> {
            ErrorScreen(
                modifier = modifier
                    .testTag(TEST_TAG_ERROR_SCREEN),
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HogwartsTheme {
        Surface {
            HomeScreen(
                navigateToCharacterScreen = {}
            )
        }
    }
}