package com.ryankoech.hogwarts.core.ktx

fun Int?.toStringOrNull() : String? {
    this?.apply {
        return this.toString()
    }
    return null
}