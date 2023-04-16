package com.codeplace.mvvmpokemonapp.ui.home.view.models

data class Pokemons(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonName>
)