package com.codeplace.mvvmpokemonapp.util

import java.util.*


fun capitalize(str: String?): String? {
        return str?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            ?: str
}