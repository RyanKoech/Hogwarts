package com.ryankoech.hogwarts.common.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme

const val TEST_TAG_LOADING_SCREEN = "TEST_TAG_LOADING_SCREEN"

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
        )
    }

}

@Preview
@Composable
fun LoadingScreenPreview() {
    HogwartsTheme {
        Surface {
            LoadingScreen()
        }
    }
}