package com.ryankoech.hogwarts.feature_home.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDto
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDtoItem
import com.ryankoech.hogwarts.feature_home.data.repository.FakeCharactersRepositoryImpl

@Composable
fun HomeScreenSuccess(
    modifier: Modifier = Modifier,
    navigateToCharacterScreen : (CharacterDtoItem) -> Unit,
    characters : CharacterDto
) {

    LazyColumn(
        modifier = modifier
    ) {

        items(characters){ character ->
            Text(text = character.name)
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
                characters = FakeCharactersRepositoryImpl.getFakeCharacterDto()
            )
        }
    }
}