package com.ryankoech.hogwarts.feature_home.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.hogwarts.common.presentation.components.CharacterImage
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme
import com.ryankoech.hogwarts.feature_home.data.dto.character_dto.CharacterDtoItem
import com.ryankoech.hogwarts.feature_home.data.repository.FakeCharactersRepositoryImpl

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterCard(
    modifier : Modifier = Modifier,
    onClick : () -> Unit,
    character: CharacterDtoItem
) {
    
    Card(
        modifier = modifier
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CharacterImage(url = character.image, name = character.name)

            Text(text = character.name)

        }

    }
    
}

@Preview
@Composable
fun CharacterCardPreview() {
    HogwartsTheme {
        Surface {
            CharacterCard(
                onClick = {},
                character = FakeCharactersRepositoryImpl.getFakeCharacterDto().first()
            )
        }
    }
}