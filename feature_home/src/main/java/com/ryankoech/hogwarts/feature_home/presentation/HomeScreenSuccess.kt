package com.ryankoech.hogwarts.feature_home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDto
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDtoItem
import com.ryankoech.hogwarts.feature_home.data.repository.FakeCharactersRepositoryImpl
import com.ryankoech.hogwarts.feature_home.presentation.components.CharacterCard
import com.ryankoech.hogwarts.feature_home.presentation.components.SearchBar

@Composable
fun HomeScreenSuccess(
    modifier: Modifier = Modifier,
    navigateToCharacterScreen : (CharacterDtoItem) -> Unit,
    characters : CharacterDto,
    searchBarValue : String,
    onSearchBarValueChange : (String) -> Unit
) {

    Column(
        modifier = modifier
            .padding(horizontal = 12.dp),
    ) {
        SearchBar(value = searchBarValue, onValueChange = onSearchBarValueChange, placeholder = "Search character...")

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(top = 8.dp, bottom = 12.dp)
        ) {

            items(characters) { character ->
                CharacterCard(
                    onClick = {
                        navigateToCharacterScreen(character)
                    },
                    character = character
                )
            }
        }
    }

}

@Preview
@Composable
fun HomeScreenSuccessPreview() {
    HogwartsTheme {
        Surface {
            HomeScreenSuccess(
                navigateToCharacterScreen = {},
                characters = FakeCharactersRepositoryImpl.getFakeCharacterDto(),
                searchBarValue = "",
                onSearchBarValueChange = {}
            )
        }
    }
}