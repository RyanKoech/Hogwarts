package com.ryankoech.hogwarts.presentation.navigation

import androidx.annotation.StringRes
import com.ryankoech.hogwarts.R


sealed class Screens(
    val route : String,
    @StringRes val titleResId : Int,
) {
    object Home : Screens(
        route = "home",
        titleResId = R.string.app_name,
    )
    object Character : Screens(
        route = "character",
        titleResId = R.string.screen_title_character,
    )
}
