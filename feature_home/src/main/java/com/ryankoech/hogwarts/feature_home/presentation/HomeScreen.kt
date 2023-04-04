package com.ryankoech.hogwarts.feature_home.presentation

import androidx.compose.material.Surface
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

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel : HomeViewModel = hiltViewModel(),
    navigateToCharacterScreen : (CharacterDtoItem) -> Unit
) {
    val viewState = viewModel.viewState.value

    when(viewState.screenState) {
        ScreenState.SUCCESS -> {
            HomeScreenSuccess(
                navigateToCharacterScreen = navigateToCharacterScreen,
                characters = viewState.characters
            )
        }
        ScreenState.LOADING -> {
            LoadingScreen()
        }
        ScreenState.ERROR -> {
            ErrorScreen()
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