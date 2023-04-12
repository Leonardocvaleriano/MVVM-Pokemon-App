package com.codeplace.mvvmpokemonapp.ui.home.view.activity.models

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Results>
)