package com.ryankoech.hogwarts.common.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ryankoech.hogwarts.common.R
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme

@Composable
fun CharacterImage(
    modifier : Modifier = Modifier,
    size : Dp = 100.dp,
    url : String,
    name : String
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center

    ) {

        AsyncImage(
            modifier = Modifier
                .padding(8.dp),
            model = url,
            contentDescription = "$name Image.",
            error = painterResource(R.drawable.placeholder),
        )
    }
}

@Preview
@Composable
fun CharacterImagePreview() {
    HogwartsTheme {
        Surface {
            CharacterImage(
                url = "",
                name = "",
            )
        }
    }
}