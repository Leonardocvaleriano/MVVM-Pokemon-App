package com.codeplace.mvvmpokemonapp.ui.home.view.adapters

import com.codeplace.mvvmpokemonapp.db.model.PokemonDb

interface PokemonFavoriteItemClickListener {

    fun removeFromFavoriteClick(
        name:String?, message:String
    )
}

