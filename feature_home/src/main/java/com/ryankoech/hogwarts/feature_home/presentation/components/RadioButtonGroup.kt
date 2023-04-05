package com.ryankoech.hogwarts.feature_home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryankoech.hogwarts.common.presentation.theme.HogwartsTheme

@Composable
fun RadioButtonGroup(
    modifier : Modifier = Modifier,
    value : String,
    values : List<String>,
    onButtonClicked : (String) -> Unit,

) {

   LazyColumn(
       modifier = modifier
   ) {

       items(values) { valueItem ->

           Row(
               modifier = Modifier
                   .fillMaxWidth(),
               verticalAlignment = Alignment.CenterVertically,
           ) {

               RadioButton(
                   selected = value == valueItem,
                   onClick = { onButtonClicked(valueItem) }
               )

               Spacer(
                   modifier = Modifier
                       .width(12.dp),
               )

               Text(
                   text = valueItem,
               )

           }
       }

   }

}

@Preview
@Composable
fun RadioButtonGroupPreview() {
    HogwartsTheme {
        Surface {
            RadioButtonGroup(
                value = "Mango",
                values = listOf("Mango", "Passion", "Orange"),
                onButtonClicked = {},
            )
        }
    }
}