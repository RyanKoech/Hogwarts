package com.ryankoech.hogwarts.feature_home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.hogwarts.common.presentation.components.CharacterImage
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme
import com.ryankoech.hogwarts.feature_home.domain.entities.CharacterEntityItem
import com.ryankoech.hogwarts.feature_home.data.repository.FakeCharactersRepositoryImpl
import com.ryankoech.hogwarts.feature_home.domain.entities.toCharacterEntity

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterCard(
    modifier : Modifier = Modifier,
    onClick : () -> Unit,
    character: CharacterEntityItem
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
                character = FakeCharactersRepositoryImpl.getFakeCharacterDto().toCharacterEntity().first()
            )
        }
    }
}