package com.codeplace.mvvmpokemonapp.ui.activity.models

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Results>
)