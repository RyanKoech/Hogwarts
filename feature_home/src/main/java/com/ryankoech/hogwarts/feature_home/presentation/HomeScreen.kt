package com.ryankoech.hogwarts.feature_home.presentation

import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ryankoech.hogwarts.common.presentation.components.ErrorScreen
import com.ryankoech.hogwarts.common.presentation.components.LoadingScreen
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme
import com.ryankoech.hogwarts.common.presentation.utils.ScreenState
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDtoItem
import com.ryankoech.hogwarts.feature_home.presentation.viewmodel.HomeViewModel

private val houses : Map<String, String> = hashMapOf(
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
        mutableStateOf(houses.entries.iterator().next().key)
    }

    fun editFilterStringState(subString :String) {
        viewModel.getCharacters(subString, houses[filterHouseState] ?: "")
        filterStringState = subString
    }


    when(viewState.screenState) {
        ScreenState.SUCCESS -> {
            HomeScreenSuccess(
                modifier = modifier,
                navigateToCharacterScreen = navigateToCharacterScreen,
                characters = viewState.characters,
                searchBarValue = filterStringState,
                onSearchBarValueChange = ::editFilterStringState,
                housesList = houses.toList().map { it.first },
                selectedHouse = filterHouseState,
                changeSelectedHouse = {
                    viewModel.getCharacters(filterStringState, houses[it] ?: "")
                    filterHouseState = it
                }
            )
        }
        ScreenState.LOADING -> {
            LoadingScreen(
                modifier = modifier,
            )
        }
        ScreenState.ERROR -> {
            ErrorScreen(
                modifier = modifier,
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