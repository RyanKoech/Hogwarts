package com.ryankoech.hogwarts.feature_home.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme
import com.ryankoech.hogwarts.feature_home.R

const val TEST_TAG_SEARCHBAR = "TEST_TAG_SEARCHBAR"
const val TEST_TAG_SEARCHBAR_FILTER_ICON = "TEST_TAG_SEARCHBAR_FILTER_ICON"

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    value : String,
    onValueChange : (String) -> Unit,
    placeholder: String,
    onTrailerCLick : () -> Unit
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
        trailingIcon = {
            IconButton(
                modifier = Modifier
                    .testTag(TEST_TAG_SEARCHBAR_FILTER_ICON),
                onClick = onTrailerCLick
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_tune),
                    contentDescription = "Filters"
                )
            }
        }
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
                placeholder = "Discover asset, coin or token",
                onTrailerCLick = {}
            )
        }
    }
}