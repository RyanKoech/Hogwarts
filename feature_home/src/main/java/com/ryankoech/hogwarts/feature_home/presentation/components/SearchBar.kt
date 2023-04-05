package com.ryankoech.hogwarts.feature_home.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme


@Composable
fun SearchBar(
    value : String,
    onValueChange : (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search icon"
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.caption,
            )
        },
        textStyle = MaterialTheme.typography.caption,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
    )


}

@Preview
@Composable
fun SearchBarPreview() {
    HogwartsTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {

            SearchBar(
                value = "",
                onValueChange = {},
                placeholder = "Discover asset, coin or token"
            )
        }
    }
}