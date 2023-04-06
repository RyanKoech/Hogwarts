package com.ryankoech.hogwarts.common.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme

const val TEST_TAG_ERROR_SCREEN = "TEST_TAG_ERROR_SCREEN"

@Composable
fun ErrorScreen(
    modifier : Modifier = Modifier
) {

    Column(
        modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Icon(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .size(56.dp),
            imageVector = Icons.Default.Warning,
            contentDescription = "An Error Occurred"
        )
        Text(
            text = "Oops. Something went wrong.",
            style = MaterialTheme.typography.h6
        )

    }

}

@Preview
@Composable
fun ErrorScreenPreview() {
    HogwartsTheme {
        Surface {
            ErrorScreen()
        }
    }
}