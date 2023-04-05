package com.ryankoech.hogwarts.common.core.ktx

import java.util.*

fun String.capitalize() : String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.ROOT
        ) else it.toString()
    }
}