package com.ryankoech.hogwarts.feature_character.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.hogwarts.common.core.ktx.capitalize
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme

@Composable
fun DetailsItems(
    modifier : Modifier = Modifier,
    title : String,
    body: String,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
    ) {

        Text(
            text = "${title.capitalize()}:",
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Medium,
            )
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            modifier = Modifier
                .alpha(0.8F),
            text = body.capitalize()
        )

    }

}

@Preview
@Composable
fun DetailsItemsPreview() {
    HogwartsTheme {
        Surface {
            DetailsItems(
                title ="Name",
                body = "Harry Potter"
            )
        }
    }
}