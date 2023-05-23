package com.codeplace.mvvmpokemonapp.ui.home.view.adapter

import android.view.View
import com.codeplace.mvvmpokemonapp.db.model.PokemonDb
import com.codeplace.mvvmpokemonapp.ui.home.view.models.Pokemon

interface RecyclerViewClickListener {
    fun onRecyclerViewIcFavoriteClick(view: View, pokemon:Pokemon, pokemonsDb: PokemonDb)
}