package com.ryankoech.hogwarts.feature_home.core.ktx

fun Int?.toStringOrNull() : String? {
    this?.apply {
        return this.toString()
    }
    return null
}