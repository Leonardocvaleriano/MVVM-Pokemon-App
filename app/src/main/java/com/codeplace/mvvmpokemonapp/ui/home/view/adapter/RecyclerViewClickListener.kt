package com.codeplace.mvvmpokemonapp.ui.home.view.adapter

import com.codeplace.mvvmpokemonapp.db.model.PokemonDb

interface RecyclerViewClickListener {



    fun addToFavoriteClick(
        pokemonDb: PokemonDb?,
        message:String?
    )

    fun removeFromFavoritesClick(
        pokemonName:String?,
        message:String?
    )

//    fun onRecyclerViewIcFavoriteClick(
//        pokemonNamePosition: Pokemon,
//        pokemonDb: PokemonDb,
//        message:String
//    )



}