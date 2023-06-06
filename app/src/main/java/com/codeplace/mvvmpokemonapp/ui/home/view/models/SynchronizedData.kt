package com.codeplace.mvvmpokemonapp.ui.home.view.models

data class SynchronizedData(
    val name: String? = "",
    val id:Int? = 0,
    val ability: String? = "",
    val type: String? = "",
    val move: String? = "",
    val imageUrl: String? = "",
    var favoriteStats: String? = "0"
)