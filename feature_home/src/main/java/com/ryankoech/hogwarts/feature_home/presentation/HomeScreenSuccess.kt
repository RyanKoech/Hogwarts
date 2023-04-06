package com.ryankoech.hogwarts.feature_home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDto
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDtoItem
import com.ryankoech.hogwarts.feature_home.data.repository.FakeCharactersRepositoryImpl
import com.ryankoech.hogwarts.feature_home.presentation.components.CharacterCard
import com.ryankoech.hogwarts.feature_home.presentation.components.RadioButtonGroup
import com.ryankoech.hogwarts.feature_home.presentation.components.SearchBar

const val TEST_TAG_HOME_SCREEN_SUCCESS = "TEST_TAG_HOME_SCREEN_SUCCESS"
const val TEST_TAG_HOME_SCREEN_SUCCESS_CLOSE_MODAL_ICON = "TEST_TAG_HOME_SCREEN_SUCCESS_CLOSE_MODAL_ICON"

@Composable
fun HomeScreenSuccess(
    modifier: Modifier = Modifier,
    navigateToCharacterScreen : (CharacterDtoItem) -> Unit,
    characters : CharacterDto,
    searchBarValue : String,
    onSearchBarValueChange : (String) -> Unit,
    housesList : List<String>,
    selectedHouse : String,
    changeSelectedHouse : (String) -> Unit,
) {

    var showModal by remember{
        mutableStateOf(false)
    }

    Box(
        modifier = modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
        ) {
            SearchBar(
                value = searchBarValue,
                onValueChange = onSearchBarValueChange,
                placeholder = "Search character...",
                onTrailerCLick = {showModal = true}
            )

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

        if(showModal) {
            Dialog(
                onDismissRequest = { showModal = false }
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    shape = MaterialTheme.shapes.medium
                ) {

                    Column(
                        modifier = Modifier
                            .padding(12.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(start = 12.dp),
                                text = "Select House",
                                style = MaterialTheme.typography.body1
                            )

                            IconButton(
                                modifier = Modifier
                                    .testTag(TEST_TAG_HOME_SCREEN_SUCCESS_CLOSE_MODAL_ICON),
                                onClick = { showModal = false }
                            ) {

                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Close Select Houses Modal"
                                )

                            }
                        }

                        RadioButtonGroup(
                            value = selectedHouse,
                            values = housesList,
                            onButtonClicked = changeSelectedHouse,
                        )
                    }
                }
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
                onSearchBarValueChange = {},
                housesList = listOf("H", "Y"),
                selectedHouse = "H",
                changeSelectedHouse = {}
            )
        }
    }
}