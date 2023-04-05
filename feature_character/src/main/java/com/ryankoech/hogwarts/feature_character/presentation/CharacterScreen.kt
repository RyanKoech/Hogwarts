package com.ryankoech.hogwarts.feature_character.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.hogwarts.common.presentation.components.CharacterImage
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme
import com.ryankoech.hogwarts.feature_character.domain.entity.HogwartsCharacter
import com.ryankoech.hogwarts.feature_character.presentation.components.DetailsItems

@Composable
fun CharacterScreen(
    modifier : Modifier = Modifier,
    character: HogwartsCharacter,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {

        CharacterImage(
            modifier = Modifier
                .align(CenterHorizontally),
            url = character.image,
            name = character.name
        )

        Text(
            modifier = Modifier
                .align(CenterHorizontally),
            text = character.name,
            style = MaterialTheme.typography.h5
        )


        DetailsItems(title = "Gender", body = character.gender)

        DetailsItems(title = "Species", body = character.species)

        DetailsItems(title = "Alive", body = if (character.alive) "Yes" else "No" )

        DetailsItems(title = "Wizard", body = if (character.wizard) "Yes" else "No" )

        DetailsItems(title = "Birth Day", body = character.dateOfBirth)

        DetailsItems(title = "Ancestry", body = character.ancestry)


    }

}

@Preview
@Composable
fun CharacterScreenPreview() {
    HogwartsTheme {
        Surface {
            CharacterScreen(
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
            )
        }
    }
}