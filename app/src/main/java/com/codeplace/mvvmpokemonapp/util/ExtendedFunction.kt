package com.codeplace.mvvmpokemonapp.util

import java.util.*


fun capitalize(str: String?): String? {
        return str?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            ?: str
}

fun convertDecimetersToMeters(decimeters:Double):Double{
    return decimeters / 10.0
}

fun convertHectogramsToKg(hectograms:Double):Double{
    return hectograms / 10.00
}




